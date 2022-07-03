package service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.dto.CustomerDTO;
import repository.CustomerRepository;
import repository.entity.CustomerEntity;
import repository.impl.CustomerRepositoryImpl;
import service.CustomerService;

public class CustomerServiceImpl implements CustomerService {

	private List<CustomerEntity> customersEntityList = new ArrayList<CustomerEntity>();
	private List<CustomerDTO> customersDTOList = new ArrayList<CustomerDTO>();
	private final static CustomerRepository<CustomerEntity> customerRepository = new CustomerRepositoryImpl();

	@Override
	public List<CustomerDTO> insert(CustomerDTO customerDTO, String fileName) {
		CustomerEntity customerEntity = new CustomerEntity();
		customerEntity.setId(customerDTO.getId());
		customerEntity.setCustomerId(customerDTO.getCustomerId());
		customerEntity.setName(customerDTO.getName());
		customerEntity.setPhone(customerDTO.getPhone());
		
		this.customersEntityList.add(customerEntity);
		
		customerRepository.writeToFile(customersEntityList, fileName);
	
		return this.convertCustomersEntityListIntoCustomersDTOList(this.customersEntityList);
	}

	@Override
	public List<CustomerDTO> findAll(String fileName) {
		this.customersEntityList = customerRepository.readDataFromFile(fileName);
		this.customersDTOList = this.customersEntityList.stream()
				.map(item -> CustomerDTO.Builder.with(item.getId(), $ -> {
					$.setCustomerId(item.getCustomerId());
					$.setName(item.getName());
					$.setPhone(item.getPhone());
				}))
				.collect(Collectors.toList());
		return this.customersDTOList;
	}

	@Override
	public List<CustomerDTO> findById(Long id, String fileName) {
		List<CustomerDTO> customersDTOListTemp = this.findAll(fileName);
		return customersDTOListTemp.stream().filter(item -> item.getId().equals(id)).collect(Collectors.toList());
	}

	public List<CustomerDTO> findByIdWithoutFile(List<CustomerDTO> customersDTOList, Long id, String fileName) {
		return customersDTOList.stream().filter(item -> item.getId().equals(id)).collect(Collectors.toList());
	}

	@Override
	public List<CustomerDTO> findByName(String name, String fileName) {
		List<CustomerDTO> customersDTOListTemp = this.findAll(fileName);
		return customersDTOListTemp.stream().filter(item -> item.getName().equals(name)).collect(Collectors.toList());
	}

	@Override
	public List<CustomerDTO> updateById(CustomerDTO customerDTO, String fileName) {

		/**
		 * 1. Find All 
		 * 2. Find by id 
		 * 3. get object and update object 
		 * 4. update object in current list 
		 * 5. save new list into file
		 */

		// 1. Find All
		this.customersDTOList = this.findAll(fileName); // update current list

		// 2. Find by id
		List<CustomerDTO> resultFindById = this.findByIdWithoutFile(this.customersDTOList, customerDTO.getId(),
				fileName);

		// 3. Get object and update object
		CustomerDTO customerIsSelected = resultFindById.get(0);
		if (!isCustomerFieldIdentical(customerIsSelected.getCustomerId(), customerDTO.getCustomerId()))
			customerIsSelected.setCustomerId(customerDTO.getCustomerId());
		if (!isCustomerFieldIdentical(customerIsSelected.getName(), customerDTO.getName()))
			customerIsSelected.setName(customerDTO.getName());
		if (!isCustomerFieldIdentical(customerIsSelected.getPhone(), customerDTO.getPhone()))
			customerIsSelected.setPhone(customerDTO.getPhone());

		// 4. update object in current list
		this.customersDTOList = this.customersDTOList.stream()
			.map(item -> {
				if(item.getId().equals(customerIsSelected.getId())) {
					item.setCustomerId(customerIsSelected.getCustomerId());
					item.setName(customerIsSelected.getName());
					item.setPhone(customerIsSelected.getPhone());
				}
				return item;
			})
			.collect(Collectors.toList());
		
		// 5. Save new list into file
		boolean isSuccess = customerRepository.removeFile(fileName);
		if (isSuccess) {
			// Convrt list DTO into list Entity for saving file
			List<CustomerEntity> customersEntityListTemp = convertCustomersDTOListIntoCustomersEntityList(
					this.customersDTOList);
			customerRepository.writeToFile(customersEntityListTemp, fileName);
		} else
			return null;

		return this.customersDTOList;
	}

	@Override
	public List<CustomerDTO> deleteById(CustomerDTO customerDTO, String fileName) {

		/**
		 * 1. Find All 2. Find by id 3. get object 4. delete object in current list 5.
		 * save new list into file
		 */

		// 1. Find All
		this.customersDTOList = this.findAll(fileName);

		// 2. Find by id
		List<CustomerDTO> resultFindById = this.findByIdWithoutFile(this.customersDTOList, customerDTO.getId(), fileName);

		// 3. Get object
		CustomerDTO customerIsSelected = resultFindById.get(0);

		// 4. delete object in current list

		this.customersDTOList = this.customersDTOList.stream()
				.filter(item -> !item.getId().equals(customerIsSelected.getId())).collect(Collectors.toList());

		// 5. Save new list into file
		boolean isSuccess = customerRepository.removeFile(fileName);
		if (isSuccess) {
			// Convert list DTO into list Entity for saving file
			List<CustomerEntity> customersEntityListTemp = convertCustomersDTOListIntoCustomersEntityList(
					this.customersDTOList);
			customerRepository.writeToFile(customersEntityListTemp, fileName);
		} else
			return null;

		return this.customersDTOList;
	}
	

	private List<CustomerEntity> convertCustomersDTOListIntoCustomersEntityList(final List<CustomerDTO> list) {
		return list.stream().map(customerDTO -> {
			CustomerEntity customerEntity = new CustomerEntity();
			customerEntity.setId(customerDTO.getId());
			customerEntity.setCustomerId(customerDTO.getCustomerId());
			customerEntity.setName(customerDTO.getName());
			customerEntity.setPhone(customerDTO.getPhone());
			return customerEntity;
		}).collect(Collectors.toList());
	}
	
	private List<CustomerDTO> convertCustomersEntityListIntoCustomersDTOList(final List<CustomerEntity> list) {
		return list.stream()
				.map(item -> CustomerDTO.Builder.with(item.getId(), $ -> {
					$.setCustomerId(item.getCustomerId());
					$.setName(item.getName());
					$.setPhone(item.getPhone()); 
				}))
				.collect(Collectors.toList());
	}

	private boolean isCustomerFieldIdentical(final String field1, final String field2) {
		return field1.equals(field2);
	}
}

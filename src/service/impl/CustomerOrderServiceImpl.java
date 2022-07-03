package service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.dto.CustomerOrderDTO;
import model.dto.CustomerProductDTO;
import repository.CustomerOrderRepository;
import repository.entity.CustomerOrderEntity;
import repository.impl.CustomerOrderRepositoryImpl;
import service.CustomerOrderService;
//
//private Long customerNumber;
//private Long productNumber;

public class CustomerOrderServiceImpl implements CustomerOrderService {
	private final static CustomerOrderRepository<CustomerOrderEntity> customerOrderRepository 	= new CustomerOrderRepositoryImpl();
	private List<CustomerOrderEntity> customersOrdersEntity 									= new ArrayList<>();
	private List<CustomerOrderDTO> customerOrderDTOList 										= new ArrayList<>();
	private List<CustomerProductDTO> customeProuducDTOList 										= new ArrayList<>();
	private List<Map<Long, Long>> customerOrderDTOListMap										= new ArrayList<>();
	private List<Map<Long, Long>> customerProductDTOListMap										= new ArrayList<>();

	@Override
	public List<CustomerOrderDTO> insert(CustomerOrderDTO customerOrderDTO, String fileName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CustomerOrderDTO> findAll(String fileName) {
		this.customersOrdersEntity = customerOrderRepository.readDataFromFile(fileName);
		this.customerOrderDTOList  = convertCustomersOrdersEntityListIntoCustomersOrdersDTOList(this.customersOrdersEntity);	
		return this.customerOrderDTOList;
	}

	@Override
	public List<Map<Long, Long>> formatFindAllReturnListMap(final String fileName) {
		List<CustomerOrderDTO> customersOrdersDTOTemp = this.findAll(fileName);
		this.customerOrderDTOListMap = Arrays.asList(customersOrdersDTOTemp.stream()
			.collect(Collectors.toMap(CustomerOrderDTO::getCustomerNumber, CustomerOrderDTO::getOrderNumber)));
		return this.customerOrderDTOListMap;
	}
	
	private List<CustomerOrderDTO> convertCustomersOrdersEntityListIntoCustomersOrdersDTOList(final List<CustomerOrderEntity> list) {
		return list.stream()
				.map(item -> new CustomerOrderDTO(item.getId(), item.getCustomerNumber(), item.getOrderNumber()))
				.collect(Collectors.toList());
	}
	
	private List<CustomerOrderEntity> convertCustomersOrdersDTOListIntoCustomersOrdersEntityList(final List<CustomerOrderDTO> list) {
		return list.stream()
				.map(item -> {
					CustomerOrderEntity customerOrderEntity = new CustomerOrderEntity();
					customerOrderEntity.setId(item.getId());
					customerOrderEntity.setCustomerNumber(item.getCustomerNumber());
					customerOrderEntity.setOrderNumber(item.getOrderNumber());
					return customerOrderEntity;
				})
				.collect(Collectors.toList());
	}
}

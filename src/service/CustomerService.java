package service;

import java.util.List;

import model.dto.CustomerDTO;

public interface CustomerService {
	List<CustomerDTO> insert(CustomerDTO customerDTO,final String fileName);
	
	List<CustomerDTO> findAll(final String fileName);
	
	List<CustomerDTO> findById(final Long id, final String fileName);
	
	List<CustomerDTO> findByName(final String name, final String fileName);
	
	List<CustomerDTO> updateById(CustomerDTO customerDTO, final String fileName);

	List<CustomerDTO> deleteById(CustomerDTO customerDTO, final String fileName);
}

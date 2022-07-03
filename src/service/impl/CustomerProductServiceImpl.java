package service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.dto.CustomerOrderDTO;
import repository.CustomerProductRepository;
import repository.entity.CustomerProductEntity;
import repository.impl.CustomerProductRepositoryImpl;
import service.CustomerProductService;

@Deprecated
public class CustomerProductServiceImpl implements CustomerProductService {

	private final static CustomerProductRepository<CustomerProductEntity> customerProductRepostiory = new CustomerProductRepositoryImpl();
	private List<CustomerProductEntity> CustomersProductsList  =  new ArrayList<>();
	
	@Override
	public List<CustomerOrderDTO> insert(CustomerOrderDTO customerOrderDTO, String fileName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CustomerOrderDTO> findAll(String fileName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<Long, Long>> formatFindAllIntoMap(String fileName) {
		// TODO Auto-generated method stub
		return null;
	}

}

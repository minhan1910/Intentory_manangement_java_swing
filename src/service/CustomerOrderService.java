package service;

import java.util.List;
import java.util.Map;

import model.dto.CustomerOrderDTO;

public interface CustomerOrderService {
	List<CustomerOrderDTO> insert(CustomerOrderDTO customerOrderDTO,final String fileName);
	
	List<CustomerOrderDTO> findAll(final String fileName);	
	
	List<Map<Long, Long>> formatFindAllReturnListMap(final String fileName);	
}

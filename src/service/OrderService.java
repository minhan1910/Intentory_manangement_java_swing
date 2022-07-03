package service;

import java.util.List;

import constant.SystemConstant;
import model.dto.CustomerDTO;
import model.dto.CustomerOrderDTO;
import model.dto.OrderDTO;
import model.dto.ProductDTO;
import repository.entity.OrderEntity;

public interface OrderService {
	List<OrderDTO> insert(OrderDTO orderDTO, final String fileName);

	List<OrderDTO> findAll(final String fileName);

	List<OrderDTO> findById(final Long id, final String fileName);

	List<ProductDTO> getProductsDTOList();

	List<CustomerDTO> getCustomersDTOList();

	List<CustomerOrderDTO> getCustomersOrdersDTOList();

	List<OrderDTO> getOrdersDTOList();
	
	boolean saveOrdersList(List<OrderDTO> ordersDTOList, final String filename);
	
//	List<OrderEntity> findByCusomerId(final Long id, final String fileName);
//
//	List<OrderEntity> findByProductId(final Long id, final String fileName);
}

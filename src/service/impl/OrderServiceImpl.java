package service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import constant.SystemConstant;
import model.dto.CustomerDTO;
import model.dto.CustomerOrderDTO;
import model.dto.OrderDTO;
import model.dto.ProductDTO;
import repository.OrderRepository;
import repository.entity.OrderEntity;
import repository.impl.OrderRepositoryImpl;
import service.CustomerOrderService;
import service.CustomerService;
import service.OrderService;
import service.ProductService;

public class OrderServiceImpl implements OrderService {

	private final static CustomerService customerService				= new CustomerServiceImpl();
	private final static ProductService productService 					= new ProductServiceImpl();
	private final static OrderRepository<OrderEntity> orderRepository 	= new OrderRepositoryImpl();
	private final static CustomerOrderService customerOrderService 		= new CustomerOrderServiceImpl();
	
	private List<ProductDTO> productsDTOList = new ArrayList<>();
	private List<CustomerDTO> customersDTOList = new ArrayList<>();
	private List<CustomerOrderDTO> customersOrdersList = new ArrayList<>();
	private List<OrderDTO> ordersDTOList = new ArrayList<>();
	private List<OrderEntity> ordersEntityList = new ArrayList<>();
	
	@Override
	public List<ProductDTO> getProductsDTOList() {
		return this.productsDTOList = productService.findAll(SystemConstant.PRODUCT_FILE);
	}
	
	@Override
	public List<CustomerDTO> getCustomersDTOList() {
		return this.customersDTOList = customerService.findAll(SystemConstant.CUSTOMER_FILE);
	}
	
	@Override
	public List<CustomerOrderDTO> getCustomersOrdersDTOList() {
		return this.customersOrdersList = customerOrderService.findAll(SystemConstant.CUSTOMER_ORDER_FILE);
	}
	
	/**
		private Long price;
		private Long quantity;
		private Long total;
		private String productName;
		getCustomerIdProductIdPair
	 * 
	 */
	@Override
	public List<OrderDTO> getOrdersDTOList() {
		this.ordersEntityList = orderRepository.readDataFromFile(SystemConstant.ORDER_FILE);
		this.ordersDTOList = convertOrdersEntityListIntoOrdersDTOList(this.ordersEntityList);
		return this.ordersDTOList;
	}

	@Override
	public List<OrderEntity> insert(OrderEntity orderDTO, String fileName) {
		
		return null;
	}

	@Override
	public List<OrderEntity> findAll(String fileName) {
		return null;
	}

	@Override
	public List<OrderEntity> findById(Long id, String fileName) {
		return null;
	}
	
	public List<OrderDTO> convertOrdersEntityListIntoOrdersDTOList(List<OrderEntity> list) {
		return list.stream()
				.map(item ->  OrderDTO.OrderDTOBuilder.with(
						item.getId(), $ -> {
							$.setCustomerIdProductIdPair(item.getCustomerIdProductIdPair());
							$.setPrice(item.getPrice());
							$.setProductName(item.getProductName());
							$.setTotal(item.getTotal());
						}
					)
				)
				.collect(Collectors.toList());
	}

}

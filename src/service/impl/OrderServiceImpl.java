package service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
	public List<OrderDTO> insert(OrderDTO orderDTO, String fileName) {
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setId(orderDTO.getId());
		orderEntity.setPrice(orderDTO.getPrice());
		orderEntity.setProductName(orderDTO.getProductName());
		orderEntity.setQuantity(orderDTO.getQuantity());
		orderEntity.setTotal(orderDTO.getTotal());
		
		this.ordersEntityList.add(orderEntity);
		
		orderRepository.writeToFile(ordersEntityList, fileName);
		this.ordersDTOList = this.ordersEntityList.stream()
				.map(item -> OrderDTO.Builder.with(item.getId(), 
						$ -> {
							$.setCustomerIdProductIdPair(item.getCustomerIdProductIdPair());
							$.setPrice(item.getPrice());
							$.setProductName(item.getProductName());
							$.setQuantity(item.getQuantity());
							$.setTotal(item.getTotal());
						}))
				.collect(Collectors.toList());
		
		return this.ordersDTOList;
	}

	@Override
	public List<OrderDTO> findAll(String fileName) {
		return null;
	}

	@Override
	public List<OrderDTO> findById(Long id, String fileName) {
		return null;
	}
	
	public List<OrderDTO> convertOrdersEntityListIntoOrdersDTOList(List<OrderEntity> list) {
		return list.stream()
				.map(item ->  OrderDTO.Builder.with(
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

	@Override
	public boolean saveOrdersList(List<OrderDTO> ordersDTOList, final String filename) {
		/**
		 * ở đây trong order nó sẽ có customerId và productId -> lấy ra được productId trong product list
		 * + trong product list sẽ update nó bởi update by id vì list order cũng sẽ có list product trong đó
		 * 
		 *  1. Update all object in product list
		 *  2. save product list
		 *  3. save order list
		 *  
		 *  -- tren UI se get them product list
		 */
		
		// 1. update all object in product 
		
		// 1.1 Get all productId in ordersDTOList
		this.productsDTOList = productService.findAll(SystemConstant.PRODUCT_FILE);
		List<Long> productNumbersList = ordersDTOList.parallelStream()
				.map(item -> item.getCustomerIdProductIdPair().getSecondaryValue())
				.collect(Collectors.toList());
		// 1.2 Update product list 
		
		// Get product number in productsDTOList
		// ==> Type  Map<100001, ProductDTO>
		Map<Long, ProductDTO> productNumbersProductsDTOListMap = 
				this.productsDTOList.parallelStream()
						.collect(Collectors.toMap(ProductDTO::getId, Function.identity()));
		
		// update product by product number list in this.productsDTOList
		this.productsDTOList = IntStream.range(0, this.productsDTOList.size())
			.mapToObj(index -> {
				ProductDTO productDTO = this.productsDTOList.get(index);
				Long productNumber = productDTO.getId();
				if(productNumbersProductsDTOListMap.containsKey(productNumber)) {
					ProductDTO productDTOInMap = productNumbersProductsDTOListMap.get(productNumber);
					productDTO.setQuantity(productDTOInMap.getQuantity());
				}
				return productDTO;
			})
			.collect(Collectors.toList());
		
		// 1.3 save product list
		this.productsDTOList = productService.insertList(this.productsDTOList, SystemConstant.PRODUCT_FILE);
		
		// 2. save order list
		orderRepository.writeToFile(this.ordersEntityList, filename);
//		this.ordersEntityList = orderRepository.readDataFromFile(filename);
//		this.ordersDTOList = this.convertOrdersEntityIntoOrdersDTO(this.ordersEntityList);
//		
		return true;
	}
	
	private List<OrderDTO> convertOrdersEntityIntoOrdersDTO(final List<OrderEntity> list) {
		return list.stream()
				.map(item -> OrderDTO.Builder.with(
						item.getId(),
						$ -> {
							$.setCustomerIdProductIdPair(item.getCustomerIdProductIdPair());
							$.setPrice(item.getPrice());
							$.setProductName(item.getProductName());
							$.setQuantity(item.getQuantity());
							$.setTotal(item.getTotal());
						}
					)
				)
				.collect(Collectors.toList());
	}

}

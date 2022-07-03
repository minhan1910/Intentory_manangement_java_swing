package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import javax.swing.JOptionPane;

import constant.OrderConstant;
import constant.SystemConstant;
import model.dto.CustomerDTO;
import model.dto.CustomerOrderDTO;
import model.dto.OrderDTO;
import model.dto.ProductDTO;
import service.OrderService;
import service.impl.OrderServiceImpl;
import utils.Pair;
import view.OrderView;

public class OrderController implements ActionListener {
	private final static OrderService orderService = new OrderServiceImpl();
	private List<OrderDTO> ordersDTOListReal = new ArrayList<>();
	private List<OrderDTO> ordersDTOListTemp = new ArrayList<>();
	private List<ProductDTO> productsDTOList = new ArrayList<>();
	private List<CustomerDTO> customersDTOList = new ArrayList<>();
	private List<CustomerOrderDTO> customersOrdersList = new ArrayList<>();
	private CustomerDTO customerDTORowSelected = new CustomerDTO();
	private ProductDTO productDTORowSelected = new ProductDTO();
	private int isCustomerDTORowSelected = -1;
	private int isProductDTORowSelected = -1;
	private OrderView orderView = null;

	public OrderController(OrderView orderView) {
		this.orderView = orderView;

		this.productsDTOList = this.getProductsDTOList();
		if (this.productsDTOList.size() > 0 && this.productsDTOList != null)
			this.orderView.setProductsDTOList(productsDTOList);

		this.customersDTOList = this.getCustomersDTOList();
		if (this.customersDTOList.size() > 0 && this.customersDTOList != null)
			this.orderView.setCustomersDTOList(customersDTOList);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String orderAction = e.getActionCommand();

		switch (orderAction) {
		case OrderConstant.ADD_TO_ORDERS: {
			if (this.getIsCustomerDTORowSelected() < 0)
				JOptionPane.showMessageDialog(orderView, "Please Choose Customer To Orders");
			if (this.getIsProductDTORowSelected() < 0)
				JOptionPane.showMessageDialog(orderView, "Please Choose Product To Orders");

			/*
			 * isCustomerDTORowSelected is the number of customer row selected
			 * isProductDTORowSelected is the number of product row selected
			 */

			OrderDTO orderDTO = this.initOrderDTO();

			this.ordersDTOListReal.add(orderDTO);
//				this.ordersDTOListTemp = this.ordersDTOListReal.stream()
//						.map(item -> OrderDTO.Builder.with($ -> {
//							$.setPrice(item.getPrice());
//							$.setProductName(item.getProductName());
//							$.setQuantity(item.getQuantity());
//							$.setTotal(item.getTotal());
//							$.setCustomerIdProductIdPair(item.getCustomerIdProductIdPair());
//						}))
//						.collect(Collectors.toList());
			this.ordersDTOListTemp.addAll(ordersDTOListReal);

			this.productsDTOList = this.handlingProductDTOQuantity(this.ordersDTOListReal);
			
			this.orderView.setOrdersDTOListTemp(this.ordersDTOListTemp);
			this.orderView.showData(this.productsDTOList, this.orderView.getProductModel());
			this.orderView.showData(this.ordersDTOListTemp, this.orderView.getOrderModel());
			
			
			
			/// loi o day
			
			break;
		}

		case OrderConstant.SAVE_ORDERS: {
			/**
			 * ở đây trong order nó sẽ có customerId và productId -> lấy ra được productId
			 * trong product list + trong product list sẽ update nó bởi update by id vì list
			 * order cũng sẽ có list product trong đó
			 */
			if (this.ordersDTOListReal == null || this.ordersDTOListReal.size() < 0) {
				JOptionPane.showMessageDialog(orderView, "Please Add To Orders Further");
				break;
			}

			orderService.saveOrdersList(this.ordersDTOListReal, SystemConstant.ORDER_FILE);
			this.ordersDTOListTemp = this.ordersDTOListReal.stream().map(item -> OrderDTO.Builder.with($ -> {
				$.setPrice(item.getPrice());
				$.setProductName(item.getProductName());
				$.setQuantity(item.getQuantity());
				$.setTotal(item.getTotal());
			})).collect(Collectors.toList());

			this.orderView.setOrdersDTOListTemp(this.ordersDTOListTemp);
			this.orderView.showData(this.ordersDTOListTemp, this.orderView.getOrderModel());
			break;
		}

		case OrderConstant.VIEW_ORDERS: {

			break;
		}

		case OrderConstant.HOME: {

			break;
		}

		default:
			break;
		}
	}

	private List<ProductDTO> handlingProductDTOQuantity(List<OrderDTO> ordersDTOList) {

		
		List<Long> productNumbersListInOrder = new ArrayList<>();
		for (OrderDTO item : ordersDTOList) {
			Long id = item.getCustomerIdProductIdPair().getSecondaryValue();
			productNumbersListInOrder.add(id);
		}

		List<Long> productQuantityListInOrder = new ArrayList<>();
		for (OrderDTO item : ordersDTOList) {
			productQuantityListInOrder.add(item.getQuantity());
		}

		// Convert into Map<productNumber, ProductDTO>
		Map<Long, ProductDTO> productNumbersProductsDTOListMap = this.productsDTOList.stream()
				.collect(Collectors.toMap(ProductDTO::getId, Function.identity()));
		
		
		// Original
		Map<Long, Long> productQuantityOriginalMap = this.productsDTOList.stream()
				.collect(Collectors.toMap(ProductDTO::getId, ProductDTO::getQuantity));
		
		// Updating quantity
		for(int i = 0; i < productNumbersListInOrder.size(); ++i) {
			Long productNumber = productNumbersListInOrder.get(i);
			Long productQuantityOrder = productQuantityListInOrder.get(i);
			Long newQuantity = 0L;
			if(productQuantityOriginalMap.containsKey(productNumber)) {
				Long productQuantityOriginal = productQuantityOriginalMap.get(productNumber);
				
				newQuantity = productQuantityOriginal - productQuantityOrder;
				
				// Updateing orginal map
				productQuantityOriginalMap.replace(productNumber, newQuantity);
			}			
		}
		
		// Duyet qua cac key cua list pro orginal map
		this.productsDTOList.clear();
		
		productNumbersProductsDTOListMap.forEach((productNumberOrginalList, productOriginal) -> {
			if(productQuantityOriginalMap.containsKey(productNumberOrginalList)) {
				Long newQuantity = productQuantityOriginalMap.get(productNumberOrginalList);
				ProductDTO newProduct = ProductDTO.Builder.with(productNumberOrginalList, $ -> {
					$.setCategoryName(productOriginal.getCategoryName());
					$.setDescription(productOriginal.getDescription());
					$.setName(productOriginal.getName());
					$.setProductId(productOriginal.getProductId());
					$.setQuantity(newQuantity);
				});
				productNumbersProductsDTOListMap.replace(productNumberOrginalList, newProduct);
				this.productsDTOList.add(newProduct);
			}
		});

		return this.productsDTOList;
	}

	private OrderDTO initOrderDTO() {
		// Name, Phone, customerNumber
		CustomerDTO customerDTO = this.orderView.getCustomerDTORowSelected();

		// "PRODID", "PRODNAME", "PRODQTY", "PRODDESC", "PRODCAT"
		ProductDTO productDTO = this.orderView.getProductDTORowSelected();

		String orderId = this.orderView.getTxtOrderId().getText();
		String orderPrice = this.orderView.getTxtOrderPrice().getText();
		String orderQuantity = this.orderView.getTxtOrderQuantity().getText();

		Long orderPriceL = Long.parseLong(orderPrice);
		Long orderQuantityL = Long.parseLong(orderQuantity);
		Long total = orderPriceL * orderQuantityL;

		Long customerId = customerDTO.getId();
		Long proudctId = productDTO.getId();
		Pair<Long, Long> customerIdProductIdPair = new Pair<Long, Long>(customerId, proudctId);

		// "Num", "Product", "Quantity", "UPrice", "Total"
		OrderDTO orderDTO = OrderDTO.Builder.with($ -> {
			$.setProductName(productDTO.getName());
			$.setQuantity(orderQuantityL);
			$.setPrice(orderPriceL);
			$.setTotal(total);
			$.setCustomerIdProductIdPair(customerIdProductIdPair);
		});

		boolean hasElementInOrdersDTOList = false;
		int sizeOfOrdersDTOList = this.ordersDTOListReal.size();
		if (sizeOfOrdersDTOList > 0)
			hasElementInOrdersDTOList = true;

		if (hasElementInOrdersDTOList) {
			Long orderNumber = this.ordersDTOListReal.get(sizeOfOrdersDTOList - 1).getId() + 1;
			orderDTO.setId(orderNumber);
		}

		return orderDTO;
	}

	private List<CustomerDTO> getCustomersDTOList() {
		return orderService.getCustomersDTOList();
	}

	private List<ProductDTO> getProductsDTOList() {
		return orderService.getProductsDTOList();
	}

	// just temporary list for rendering ui
	private List<OrderDTO> getOrdersDTOList() {
		return this.ordersDTOListTemp;
	}

	public int getIsCustomerDTORowSelected() {
		return isCustomerDTORowSelected;
	}

	public void setIsCustomerDTORowSelected(int isCustomerDTORowSelected) {
		this.isCustomerDTORowSelected = isCustomerDTORowSelected;
	}

	public int getIsProductDTORowSelected() {
		return isProductDTORowSelected;
	}

	public void setIsProductDTORowSelected(int isProductDTORowSelected) {
		this.isProductDTORowSelected = isProductDTORowSelected;
	}

}

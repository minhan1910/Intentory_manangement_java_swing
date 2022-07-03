package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import constant.OrderConstant;
import constant.SystemConstant;
import model.dto.CustomerDTO;
import model.dto.CustomerOrderDTO;
import model.dto.OrderDTO;
import model.dto.ProductDTO;
import service.OrderService;
import service.impl.OrderServiceImpl;
import view.OrderView;

public class OrderController implements ActionListener {
	private final static OrderService orderService 		= new OrderServiceImpl();
	private List<OrderDTO> ordersDTOList 				= new ArrayList<>();
	private List<ProductDTO> productsDTOList 			= new ArrayList<>();
	private List<CustomerDTO> customersDTOList 			= new ArrayList<>();
	private List<CustomerOrderDTO> customersOrdersList 	= new ArrayList<>();
	private OrderView orderView = null;
	
	public OrderController(OrderView orderView) {
		this.orderView = orderView;
		
		this.productsDTOList = this.getProductsDTOList();
		if(this.productsDTOList.size() > 0 && this.productsDTOList != null)
			this.orderView.setProductsDTOList(productsDTOList);
		
		this.customersDTOList = this.getCustomersDTOList();
		if(this.customersDTOList.size() > 0 && this.customersDTOList != null)
			this.orderView.setCustomersDTOList(customersDTOList);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String orderAction = e.getActionCommand();
		
		switch (orderAction) {
			case OrderConstant.ADD_TO_ORDERS: {
				
				break;
			}
				
			case OrderConstant.SAVE_ORDERS: {
				
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
	
	private List<CustomerDTO> getCustomersDTOList() {
		return orderService.getCustomersDTOList();
	}
	
	private List<ProductDTO> getProductsDTOList() {
		return orderService.getProductsDTOList();
	}
	
	// just temporary list for rendering ui
	private List<OrderDTO> getOrdersDTOList() {
		return this.ordersDTOList;
	}
}

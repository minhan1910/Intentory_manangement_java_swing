package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import constant.CustomerConstant;
import constant.SystemConstant;
import model.dto.CustomerDTO;
import service.CustomerService;
import service.impl.CustomerServiceImpl;
import view.CustomerView;

public class CustomerController implements ActionListener {

	private List<CustomerDTO> customersDTOList = new ArrayList<CustomerDTO>();
	private final static CustomerService customerService = new CustomerServiceImpl();
	private CustomerView customerView = null;

	public CustomerController(CustomerView customerView) {
		this.customerView = customerView;

		this.customersDTOList = this.getAllCustomersDTOList();
		if (this.customersDTOList.size() > 0 || this.customersDTOList != null)
			this.customerView.setCustomersDTOList(customersDTOList);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String customerAction = e.getActionCommand();
		System.out.println(customerAction);
		switch (customerAction) {
		case CustomerConstant.ADD: {
			CustomerDTO customerDTO = this.initCustomerDTO();

			this.customersDTOList = customerService.insert(customerDTO, SystemConstant.CUSTOMER_FILE);

			this.customerView.setCustomersDTOList(customersDTOList);
			this.customerView.showData(customersDTOList, this.customerView.getModel());
			break;
		}

		case CustomerConstant.EDIT: {
			CustomerDTO customerDTO = this.initCustomerDTO();

			// Using for editing
			Long currentNumber = this.customerView.getCurrentNumber();
			customerDTO.setId(currentNumber);

			this.customersDTOList = customerService.updateById(customerDTO, SystemConstant.CUSTOMER_FILE);

			this.customerView.setCustomersDTOList(this.customersDTOList);
			this.customerView.showData(this.customersDTOList, this.customerView.getModel());
			break;
		}

		case CustomerConstant.DELETE: {
			CustomerDTO customerDTO = this.initCustomerDTO();

			// Using for editing
			Long currentNumber = this.customerView.getCurrentNumber();
			customerDTO.setId(currentNumber);

			this.customersDTOList = customerService.deleteById(customerDTO, SystemConstant.CUSTOMER_FILE);

			this.customerView.setCustomersDTOList(this.customersDTOList);
			this.customerView.showData(this.customersDTOList, this.customerView.getModel());
			break;
		}

		case CustomerConstant.REFRESH: {
			this.customerView.resetAllField();
			this.customersDTOList = customerService.findAll(SystemConstant.CUSTOMER_FILE);
			this.customerView.setCustomersDTOList(this.customersDTOList);
			this.customerView.showData(this.customersDTOList, this.customerView.getModel());
			break;
		}

		case CustomerConstant.HOME: {

			break;
		}

		default:
			break;
		}
	}

	private CustomerDTO initCustomerDTO() {
		CustomerDTO customerDTO = new CustomerDTO();
		String customerName = this.customerView.getTxtName().getText();
		String customerPhone = this.customerView.getTxtPhone().getText();
		String customerId = this.customerView.getTxtCustomerId().getText();

		int customersDTOListSize = this.customersDTOList.size();
		if (customersDTOListSize < 0)
			customerDTO.setId(this.customersDTOList.get(customersDTOListSize - 1).getId() + 1);
		customerDTO.setName(customerName);
		customerDTO.setPhone(customerPhone);
		customerDTO.setCustomerId(customerId);

		return customerDTO;
	}

	private List<CustomerDTO> getAllCustomersDTOList() {
		return customerService.findAll(SystemConstant.CUSTOMER_FILE);
	}

}

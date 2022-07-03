package model.dto;

public class CustomerOrderDTO extends BaseDTO {
	private Long customerNumber;
	private Long orderNumber;
	private static Long sId = 100000L;

	public CustomerOrderDTO() {
		sId++;
		super.setId(sId);
	}

	public CustomerOrderDTO(Long id, Long customerNumber, Long orderNumber) {
		this();
		super.setId((id != sId) ? id : sId);
		this.customerNumber = customerNumber;
		this.orderNumber = orderNumber;
	}

	public Long getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(Long customerNumber) {
		this.customerNumber = customerNumber;
	}

	public Long getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(Long orderNumber) {
		this.orderNumber = orderNumber;
	}

	

}

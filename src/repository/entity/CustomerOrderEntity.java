package repository.entity;

public class CustomerOrderEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private Long customerNumber;
	private Long orderNumber;

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

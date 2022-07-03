package repository.entity;

public class CustomerProductEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private Long customerNumber;
	private Long productNumber;

	public Long getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(Long customerNumber) {
		this.customerNumber = customerNumber;
	}

	public Long getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(Long productNumber) {
		this.productNumber = productNumber;
	}
}

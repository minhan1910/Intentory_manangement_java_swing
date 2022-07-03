package model.dto;

public class CustomerProductDTO extends BaseDTO {
	private Long customerNumber;
	private Long productNumber;
	private static Long sId = 100000L;

	public CustomerProductDTO() {
		sId++;
		super.setId(sId);
	}

	public CustomerProductDTO(Long id, Long customerNumber, Long productNumber) {
		this();
		super.setId((id != sId) ? id : sId);
		this.customerNumber = customerNumber;
		this.productNumber = productNumber;
	}

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

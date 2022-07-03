package repository.entity;

import utils.Pair;

public class OrderEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;
	private Long price;
	private Long quantity;
	private Long total;
	private String productName;
	private Pair<Long, Long> CustomerIdProductIdPair;

	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Pair<Long, Long> getCustomerIdProductIdPair() {
		return CustomerIdProductIdPair;
	}
	public void setCustomerIdProductIdPair(Pair<Long, Long> customerIdProductIdPair) {
		CustomerIdProductIdPair = customerIdProductIdPair;
	}
	
}

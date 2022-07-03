package model.dto;

import java.util.function.Consumer;

import utils.Pair;

public class OrderDTO extends BaseDTO {
	private Long price;
	private Long quantity;
	private Long total;
	private String productName;
	private Pair<Long, Long> customerIdProductIdPair; // For rendering view
	private static Long sId = 100000L;

	public OrderDTO() {
		sId++;
		super.setId(sId);
	}

	public OrderDTO(Builder orderDTOBuilder) {
		this();
		this.price = orderDTOBuilder.price;
		this.quantity = orderDTOBuilder.quantity;
		this.total = orderDTOBuilder.total;
		this.productName = orderDTOBuilder.productName;
		this.customerIdProductIdPair = orderDTOBuilder.customerIdProductIdPair;
	}
	
	// Should using builder pattern in this case
	public OrderDTO(Long id, Builder orderDTOBuilder) {
		this();
		super.setId((id != sId) ? id : sId);
		this.price = orderDTOBuilder.price;
		this.quantity = orderDTOBuilder.quantity;
		this.total = orderDTOBuilder.total;
		this.productName = orderDTOBuilder.productName;
		this.customerIdProductIdPair = orderDTOBuilder.customerIdProductIdPair;
	}
	
	public static class Builder {
		private Long price;
		private Long quantity;
		private Long total;
		private String productName;
		private Pair<Long, Long> customerIdProductIdPair; 
		
		private Builder() {}
		
		public OrderDTO build() {
			return new OrderDTO(this);
		}
		
		public OrderDTO build(Long id) {
			return new OrderDTO(id, this);
		}
		
		public static OrderDTO with(Consumer<Builder> builderConsumer) {
			Builder orderDTOBuilder = new Builder();
			builderConsumer.accept(orderDTOBuilder);
			return orderDTOBuilder.build();
		}
		
		public static OrderDTO with(Long id, Consumer<Builder> builderConsumer) {
			Builder orderDTOBuilder = new Builder();
			builderConsumer.accept(orderDTOBuilder);
			return orderDTOBuilder.build(id);
		}

		public void setPrice(Long price) {
			this.price = price;
		}

		public void setQuantity(Long quantity) {
			this.quantity = quantity;
		}

		public void setTotal(Long total) {
			this.total = total;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}

		public void setCustomerIdProductIdPair(Pair<Long, Long> customerIdProductIdPair) {
			this.customerIdProductIdPair = customerIdProductIdPair;
		}

		
	}

	public static Long getsId() {
		return sId;
	}

	public static void setsId(Long sId) {
		OrderDTO.sId = sId;
	}

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
		return customerIdProductIdPair;
	}

	public void setCustomerIdProductIdPair(Pair<Long, Long> customerIdProductIdPair) {
		this.customerIdProductIdPair = customerIdProductIdPair;
	}

	
	
}

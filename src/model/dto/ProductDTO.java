package model.dto;

import java.util.function.Consumer;

public class ProductDTO extends BaseDTO {
	private String productId;
	private String name;
	private Long quantity;
	private String description;
	private String categoryName;
	private static Long sId = 100000L;

	public ProductDTO() {
		ProductDTO.sId++;
		super.setId(sId);
	}
	
	public ProductDTO(Builder builder) {
		this();
		this.productId = builder.productId;
		this.name = builder.name;
		this.quantity = builder.quantity;
		this.description = builder.description;
		this.categoryName = builder.categoryName;
	}

	public ProductDTO(Long id, Builder builder) {
		this();
		super.setId((id != sId) ? id : sId);
		this.productId = builder.productId;
		this.name = builder.name;
		this.quantity = builder.quantity;
		this.description = builder.description;
		this.categoryName = builder.categoryName;
	}

	public static class Builder {
		private String productId;
		private String name;
		private Long quantity;
		private String description;
		private String categoryName;
		
		private Builder() {}
		
		public ProductDTO build() {
			return new ProductDTO(this);
		}
		
		public ProductDTO build(Long id) {
			return new ProductDTO(id, this);
		}
		
		public static ProductDTO with(Consumer<Builder> builderConsumer) {
			Builder builder = new Builder();
			builderConsumer.accept(builder);
			return builder.build();
		}
		
		public static ProductDTO with(Long id, Consumer<Builder> builderConsumer) {
			Builder builder = new Builder();
			builderConsumer.accept(builder);
			return builder.build(id);
		}

		public void setProductId(String productId) {
			this.productId = productId;
		}

		public void setName(String name) {
			this.name = name;
		}

		public void setQuantity(Long quantity) {
			this.quantity = quantity;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName;
		}

	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static Long getsId() {
		return sId;
	}

	public static void setsId(Long sId) {
		ProductDTO.sId = sId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

}

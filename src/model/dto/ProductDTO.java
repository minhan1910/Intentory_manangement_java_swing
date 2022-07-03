package model.dto;

import java.io.Serializable;

public class ProductDTO extends BaseDTO   {
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

	public ProductDTO(Long id, String productId,  String name, Long quantity, String description, String categoryName) {
		this();
		super.setId((id != sId) ? id : sId);
		this.productId = productId;
		this.name = name;
		this.quantity = quantity;
		this.description = description;
		this.categoryName = categoryName;
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

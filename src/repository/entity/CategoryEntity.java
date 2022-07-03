package repository.entity;

public class CategoryEntity extends BaseEntity  {
	private static final long serialVersionUID = 1L;
	private String categoryId;
	private String name;

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

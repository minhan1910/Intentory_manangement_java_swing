package model.dto;

public class CategoryDTO extends BaseDTO  {
	private String categoryId;
	private String name;
	private static Long sId = 100000L;
	
	public CategoryDTO() {
		CategoryDTO.sId++;
		super.setId(sId);
	}
	
	public CategoryDTO(Long id, String categoryId, String name) {
		this();
		super.setId((id != sId) ? id : sId);
		this.categoryId = categoryId;
		this.name = name;
	}

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

	public static Long getsId() {
		return sId;
	}

	public static void setsId(Long sId) {
		CategoryDTO.sId = sId;
	}
	
}

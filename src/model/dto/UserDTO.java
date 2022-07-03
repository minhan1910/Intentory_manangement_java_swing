package model.dto;

public class UserDTO extends BaseDTO {
	private String username;
	private String password;
	private String phone;
	private static Long sId = 100000L;
	
	public UserDTO() {
		sId++;
		super.setId(sId);
	}
	
	public UserDTO(Long id, String username, String password, String phone) {
		this();
		super.setId((id != sId) ? id : sId);
		this.username = username;
		this.password = password;
		this.phone = phone;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}

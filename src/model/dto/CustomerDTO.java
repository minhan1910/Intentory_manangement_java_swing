package model.dto;

import java.util.function.Consumer;

public class CustomerDTO extends BaseDTO {
	private String customerId;
	private String name;
	private String phone;
	private static Long sId = 100000L;

	public CustomerDTO() {
		sId++;
		super.setId(sId);
	}
	
	public CustomerDTO(Builder customerDTOBuilder) {
		super.setId(sId);
		this.customerId = customerDTOBuilder.customerId;
		this.name = customerDTOBuilder.name;
		this.phone = customerDTOBuilder.phone;
	}

	public CustomerDTO(Long id, Builder customerDTOBuilder) {
		this();
		super.setId((id != sId) ? id : sId);
		this.customerId = customerDTOBuilder.customerId;
		this.name = customerDTOBuilder.name;
		this.phone = customerDTOBuilder.phone;
	}
	
	public static class Builder {
		private String customerId;
		private String name;
		private String phone;
		
		private Builder() {}
		
		public CustomerDTO build() {
			return new CustomerDTO(this);
		}
		
		public CustomerDTO build(Long id) {
			return new CustomerDTO(id, this);
		}
		
		public static CustomerDTO with(Consumer<Builder> builderConsumer) {
			Builder customerDTOBuilder = new Builder();
			builderConsumer.accept(customerDTOBuilder);
			return customerDTOBuilder.build();
		}
		
		public static CustomerDTO with(Long id, Consumer<Builder> builderConsumer) {
			Builder customerDTOBuilder = new Builder();
			builderConsumer.accept(customerDTOBuilder);
			return customerDTOBuilder.build(id);
		}
		
		public void setCustomerId(String customerId) {
			this.customerId = customerId;
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
		
		
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public static Long getsId() {
		return sId;
	}

	public static void setsId(Long sId) {
		CustomerDTO.sId = sId;
	}

}

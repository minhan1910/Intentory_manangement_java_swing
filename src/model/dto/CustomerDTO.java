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
	
	public CustomerDTO(CustomerDTOBuilder customerDTOBuilder) {
		this();
		customerDTOBuilder.customerId = customerId;
		customerDTOBuilder.name = name;
		customerDTOBuilder.phone = phone;
	}

	public CustomerDTO(Long id, CustomerDTOBuilder customerDTOBuilder) {
		this();
		super.setId((id != sId) ? id : sId);
		customerDTOBuilder.customerId = customerId;
		customerDTOBuilder.name = name;
		customerDTOBuilder.phone = phone;
	}
	
	public static class CustomerDTOBuilder {
		private String customerId;
		private String name;
		private String phone;
		
		private CustomerDTOBuilder() {}
		
		public CustomerDTO build() {
			return new CustomerDTO(this);
		}
		
		public static CustomerDTO with(Consumer<CustomerDTOBuilder> builderConsumer) {
			CustomerDTOBuilder customerDTOBuilder = new CustomerDTOBuilder();
			builderConsumer.accept(customerDTOBuilder);
			return customerDTOBuilder.build();
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

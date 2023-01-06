package com.masai.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressDto {
		
	@Size(min = 1, message = "Invalid street")
	private String streetNo;
	
	private String buildingName;
	
	@NotNull(message = "City cannot be null.")
	@NotBlank(message = "City cannot be blank.")
	@NotEmpty(message = "City cannot be empty.")
	private String city;
	
	@NotNull(message = "State cannot be null.")
	@NotBlank(message = "State cannot be blank.")
	@NotEmpty(message = "State cannot be empty.")
	private String state;
	
	@NotNull(message = "Country cannot be null.")
	@NotBlank(message = "Country cannot be blank.")
	@NotEmpty(message = "Country cannot be empty.")
	private String country;
	
	@Size(min = 6, max = 6, message = "Invalid pin code")
	private Integer pincode;

	
	public AddressDto(
			@Size(min = 1, message = "Invalid street") String streetNo,
			String buildingName,
			@NotNull(message = "City cannot be null.") @NotBlank(message = "City cannot be blank.") @NotEmpty(message = "City cannot be empty.") String city,
			@NotNull(message = "State cannot be null.") @NotBlank(message = "State cannot be blank.") @NotEmpty(message = "State cannot be empty.") String state,
			@NotNull(message = "Country cannot be null.") @NotBlank(message = "Country cannot be blank.") @NotEmpty(message = "Country cannot be empty.") String country,
			@Size(min = 6, max = 6, message = "Invalid pin code") Integer pincode) {
		super();
		this.streetNo = streetNo;
		this.buildingName = buildingName;
		this.city = city;
		this.state = state;
		this.country = country;
		this.pincode = pincode;
	}
	
	
	
}

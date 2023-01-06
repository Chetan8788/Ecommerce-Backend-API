package com.masai.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer customerId;
	
	@Size(min = 1, max = 15, message = "Invalid first name, first name size must be less than 15")
	private String firstName;
	
	@Size(min = 1, max = 15, message = "Invalid last name, last name size must be less than 15")
	private String lastName;
	
	@Size(min = 10, max = 10, message = "Mobile Number should contain 10 digits")
	private String mobileNumber;
	
	@Size(min = 4, max = 16, message = "Password length must be between 4 to 16 characters")
	private String password;
	
	@Email(message = "Invalid email")
	private String email;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinTable(name = "c_address", joinColumns = @JoinColumn(name="customer_id", referencedColumnName = "customerId"))
	private Address address;

	public Customer(
			@Size(min = 1, max = 15, message = "Invalid first name, first name size must be less than 15") String firstName,
			@Size(min = 1, max = 15, message = "Invalid last name, last name size must be less than 15") String lastName,
			@Size(min = 10,max = 10, message = "Mobile Number should contain 10 digits") String mobileNumber,
			@Size(min = 4, max = 16, message = "Password length must be between 4 to 16 characters") String password,
			@Email(message = "Invalid email") String email, Address address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.password = password;
		this.email = email;
		this.address = address;
	}

}


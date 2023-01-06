package com.masai.controller;

import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.CustomerException;
import com.masai.exception.LoginException;
import com.masai.model.Customer;
import com.masai.service.CustomerService;


@CrossOrigin(origins = "*")
@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/customers")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) throws CustomerException{

		Customer addedCustomer = customerService.addCustomer(customer);
		
		 return new ResponseEntity<Customer>(addedCustomer, HttpStatus.CREATED);
		
	}
	
	@PutMapping("/customers/{key}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable("key") String key,@RequestBody Customer customer) throws LoginException, CustomerException{
		
		Customer updatedCustomer = customerService.updateCustomer(customer, key);
		
		return new ResponseEntity<Customer>(updatedCustomer, HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/customers/{key}")
	public ResponseEntity<Customer> removeCustomer(@PathVariable("key") String key,@RequestBody Customer customer) throws CustomerException, LoginException{
		
		Customer deletedCustomer = customerService.removeCustomer(customer, key);
		
		return new ResponseEntity<Customer>(deletedCustomer, HttpStatus.OK);
		
	}
	
	@GetMapping("/customers/{customerid}")
	public ResponseEntity<Customer> getCustomer(@PathVariable("customerid") Integer customerId) throws CustomerException{
		
		Customer existingCustomer = customerService.viewCustomer(customerId);
		
		return new ResponseEntity<Customer>(existingCustomer, HttpStatus.OK);
		
	}
	
	@GetMapping("/getallcustomers")
	public ResponseEntity<List<Customer>> getAllCustomers() throws CustomerException{
		
		List<Customer> customers = customerService.viewAllCustomer();
		
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
		
	}

	
}


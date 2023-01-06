package com.masai.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.CustomerException;
import com.masai.exception.LoginException;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.repository.AddressRepo;
import com.masai.repository.CurrentUserSessionDao;
import com.masai.repository.CustomerRepo;
import com.masai.service.CurrentUserSessionService;
import com.masai.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepo customerDao;
	
	@Autowired
	CurrentUserSessionService currentUserSessionService;
	
	@Autowired
	CurrentUserSessionDao currentUserSessionDao;
	
	@Autowired
	AddressRepo addressDao ;
	
	@Override
	public Customer addCustomer(Customer cust) throws CustomerException {
		
		Optional<Customer> opt = customerDao.findByMobileNumber(cust.getMobileNumber()) ;
		
		if(opt.isPresent()) {
			throw new CustomerException("Customer already Exist With this Mobile Number");
		}
		
		return customerDao.save(cust);
	}

	@Override
	public Customer updateCustomer(Customer cust, String key) throws CustomerException, LoginException {
		
		Customer customerDetails = currentUserSessionService.getCustomerDetails(key) ;
		
		if(customerDetails == null) {
			throw new LoginException("No user Found | Login first");
		}else if( cust.getMobileNumber().toCharArray().length != 10 ){
			
			throw new CustomerException("Mobile Number can only be of 10 digit");
		}
		
		if(cust.getCustomerId() == customerDetails.getCustomerId()) {
			return customerDao.save(cust) ;
		}
		else {
			throw new CustomerException("Can't change UserID!") ;
		}
		
	}

	@Override
	public Customer removeCustomer(Customer cust, String key) throws CustomerException, LoginException {
		
		Customer currentCustomer = currentUserSessionService.getCustomerDetails(key);
		
		if(currentCustomer != null) {
			
			if(cust.getCustomerId() == currentCustomer.getCustomerId()) {
				
				customerDao.delete(cust);
				
				Optional<CurrentUserSession> opt = currentUserSessionDao.findByUuid(key) ;
				
				CurrentUserSession currentSession = opt.get();
				
				currentUserSessionDao.delete(currentSession);
				return cust;
				
				
			}
			else {
				throw new CustomerException("Invalid Customer ID") ;
			}
			
		}
		else {
			throw new CustomerException("Invalid UUID key");
		}
		
	}

	
	@Override
	public Customer viewCustomer(Integer customerId) throws CustomerException {
		
		Optional<Customer> cust = customerDao.findById(customerId);
		
		cust.orElseThrow(()-> new CustomerException("Customer doesn't found..."));
		
		return cust.get();
		
	}

	@Override
	public List<Customer> viewAllCustomer() throws CustomerException {
		
		
		List<Customer> list = customerDao.findAll();
		
		if(!list.isEmpty()) {
			return list;
		} else {
			throw new CustomerException("No customers found");
		}
		
			
	}



}

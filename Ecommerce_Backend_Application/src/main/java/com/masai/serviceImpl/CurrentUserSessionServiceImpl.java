package com.masai.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.LoginException;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.repository.CurrentUserSessionDao;
import com.masai.repository.CustomerRepo;
import com.masai.service.CurrentUserSessionService;

@Service
public class CurrentUserSessionServiceImpl implements CurrentUserSessionService {

	@Autowired
	private CurrentUserSessionDao currentUserSessionDao;
	
	@Autowired
	private CustomerRepo customerDao;
	
	@Override
	public CurrentUserSession getCurrentUserSession(String key) throws LoginException {
		
		Optional<CurrentUserSession> currentUser = currentUserSessionDao.findByUuid(key);
		
		if(!currentUser.isPresent()) {
			throw new  LoginException("User has not logged in");
		}
		
		return currentUser.get();
	}

	@Override
	public Integer getCurrentUserCustomerId(String key) throws LoginException {
		Optional<CurrentUserSession> currentUser = currentUserSessionDao.findByUuid(key);
		
		if(!currentUser.isPresent()) {
			throw new  LoginException("User has not logged in");
		}
		
		return currentUser.get().getCustomerId();
	}

	@Override
	public Customer getCustomerDetails(String key) throws LoginException {
		Optional<CurrentUserSession> currentUser = currentUserSessionDao.findByUuid(key);
		
		if(!currentUser.isPresent()) {
			throw new  LoginException("User has not logged in");
		}
		
		Integer customerId = currentUser.get().getCustomerId();
		
		return customerDao.findById(customerId).get();
	}

}

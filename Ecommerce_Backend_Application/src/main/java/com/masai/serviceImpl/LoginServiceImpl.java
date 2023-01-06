package com.masai.serviceImpl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.CustomerException;
import com.masai.exception.LoginException;
import com.masai.exception.UserException;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.model.User;
import com.masai.repository.CurrentUserSessionDao;
import com.masai.repository.CustomerRepo;
import com.masai.service.CurrentUserSessionService;
import com.masai.service.LoginService;

import net.bytebuddy.utility.RandomString;

@Service
public class LoginServiceImpl implements LoginService{

	@Autowired
	CustomerRepo customerDao;

	@Autowired
	CurrentUserSessionDao currentUserSessionDao;

	@Autowired
	CurrentUserSessionService currentUserSessionService;
	
	@Override
	public CurrentUserSession addUser(User user) throws UserException, CustomerException {
		Optional<Customer> opt = customerDao.findByMobileNumber(user.getUserId()) ;
		
		if(opt.isEmpty()) {
			throw new CustomerException("User not found with Mobile number : "+user.getUserId());
		}
		
		Customer currentCustomer = opt.get();
		
		Integer customerId = currentCustomer.getCustomerId();
		
		Optional<CurrentUserSession> currentUserOptional = currentUserSessionDao.findByCustomerId(customerId);
		
		if(currentUserOptional.isPresent()) {
			throw new UserException("User has already logged in with userId : " + user.getUserId());
		}
		if(currentCustomer.getMobileNumber().equals(user.getUserId()) && currentCustomer.getPassword().equals(user.getPassword())) {
			
			String key = RandomString.make(6) ;
			
			CurrentUserSession currentUserSession = new CurrentUserSession(customerId, key, LocalDateTime.now()) ;
			
			return  currentUserSessionDao.save(currentUserSession) ;
			
			
		}
		else {
			throw new UserException("Invalid UserId OR Password");
		}
	}

	@Override
	public String signOut(String key) throws UserException, LoginException {
		CurrentUserSession userSession = currentUserSessionService.getCurrentUserSession(key);
		
		if(userSession != null) {
			
			currentUserSessionDao.delete(userSession);

			
			return "Logged out...";
		}
		else {
			throw new UserException("Having some problem to logout");
		}
	}

	@Override
	public User removeUser(User user, String key) throws UserException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User validateUser(User user, String key) throws UserException {

		Optional<CurrentUserSession> opt = currentUserSessionDao.findByUuid(key) ;
		
		if(opt.isEmpty()) {
			throw new UserException("Invalid Key");
		}
		
		CurrentUserSession currentUser = opt.get();
		
		Optional<Customer> currentCustomerOpt = customerDao.findById(currentUser.getCustomerId()) ;
		
		Customer currentCustomer = currentCustomerOpt.get();
		
		if(user.getUserId().equals(currentCustomer.getMobileNumber()) && user.getPassword().equals(currentCustomer.getPassword())) {
			return user;
		}
		else {
			throw new UserException("Invalid Mobile Number or Password");
		}

	}

}

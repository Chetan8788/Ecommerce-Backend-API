package com.masai.service;

import com.masai.exception.CustomerException;
import com.masai.exception.LoginException;
import com.masai.exception.UserException;
import com.masai.model.CurrentUserSession;
import com.masai.model.User;

public interface LoginService {

	public CurrentUserSession addUser(User user) throws UserException, CustomerException ;
	
	public User removeUser(User user,String key) throws UserException ;
	
	public User validateUser(User user,String key) throws UserException, LoginException ;
	
	public String signOut(String key) throws UserException, LoginException ;
	
}

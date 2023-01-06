package com.masai.controller;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exception.CustomerException;
import com.masai.exception.LoginException;
import com.masai.exception.UserException;
import com.masai.model.CurrentUserSession;
import com.masai.model.User;
import com.masai.service.LoginService;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class LoginController {
	
	
	@Autowired
	private LoginService loginService;
	
	@PostMapping("/login")
	public ResponseEntity<CurrentUserSession> addUser(@RequestBody User user) throws UserException, CustomerException {

		CurrentUserSession currentSession = loginService.addUser(user);
				
		return new ResponseEntity<CurrentUserSession>(currentSession,HttpStatus.CREATED) ;
	}
	
	@DeleteMapping("/logout")
	public ResponseEntity<String> logoutUser(@RequestParam String key) throws UserException, LoginException {

		String response = loginService.signOut(key);
		
		return new ResponseEntity<String>(response,HttpStatus.OK) ;
		
	}
	
	
}

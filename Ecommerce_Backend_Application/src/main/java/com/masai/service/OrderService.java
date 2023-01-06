package com.masai.service;

import java.time.LocalDate;
import java.util.List;

import com.masai.exception.AddressException;
import com.masai.exception.CartException;
import com.masai.exception.LoginException;
import com.masai.exception.OrderException;
import com.masai.model.Orders;

public interface OrderService {

	
	public Orders addOrder(Orders order, String key) throws OrderException, CartException, LoginException;
	public Orders updateOrder(Orders order, String key) throws OrderException, LoginException;
	public Orders removeOrder(Integer oriderId, String key) throws OrderException;
	public Orders viewOrder(Integer orderId) throws OrderException;
	public List<Orders> viewAllOrdersByDate(LocalDate date) throws OrderException;
	public List<Orders> viewAllOrdersByLocation(String city) throws OrderException, AddressException;
	public List<Orders> viewAllOrdersByUserId(String userid) throws OrderException;


}

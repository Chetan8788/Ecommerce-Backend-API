package com.masai.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.masai.exception.OrderException;
import com.masai.model.Orders;

@Repository
public interface OrderRepo extends JpaRepository<Orders, Integer>{
	
	
	public List<Orders> findByOrderDate(LocalDate date) throws OrderException;
	
	@Query("select o from Orders o where o.orderAddress.city= ?1")
	public List<Orders> getOrderByCity(String city) throws OrderException;
	
	@Query("select o from Orders o where o.customer.mobileNumber=?1")
	public List<Orders> getOrdersByUserId(String userID);
	

}
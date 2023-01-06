package com.masai.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.AddressException;
import com.masai.exception.CartException;
import com.masai.exception.LoginException;
import com.masai.exception.OrderException;
import com.masai.model.Address;
import com.masai.model.AddressDto;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.model.Orders;
import com.masai.model.ProductDto;
import com.masai.repository.CartRepo;
import com.masai.repository.CurrentUserSessionDao;
import com.masai.repository.CustomerRepo;
import com.masai.repository.OrderRepo;
import com.masai.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepo oDao;
	
	@Autowired
	CurrentUserSessionDao uSessionDao;
	
	@Autowired
	CustomerRepo cDao;
	
	@Autowired
	CartRepo cartRepo;

	@Override
	public Orders addOrder(Orders order, String key) throws OrderException, CartException, LoginException {
		
		 Optional<CurrentUserSession> user = uSessionDao.findByUuid(key);
		 
		 if( user.isPresent() ) {
			 
			 Integer customerId = user.get().getCustomerId();
			 
			 Optional<Customer> ourCustomer = cDao.findById(customerId);
			 
			 Address addr = ourCustomer.get().getAddress();
			 
			 
			 Orders currOrder = new Orders();
			 
			 currOrder.setOrderDate(LocalDate.now());
			 currOrder.setOrderAddress(new AddressDto(addr.getStreetNo(), addr.getBuildingName(), addr.getCity(), addr.getState(), addr.getCountry(), addr.getPincode()));
			 
			 currOrder.setCustomer(ourCustomer.get());
			 currOrder.setOrderStatus("Order confirmed");
			 List<ProductDto> list = cartRepo.getCart(customerId).getProducts();
			 if( list.size() < 1) {
				 throw new CartException("Add product to the cart first...");
			 }
			 currOrder.setPList(list);
			 
			 return oDao.save(currOrder);
			 
		 }
		 else {
			 throw new LoginException("Login first");
		 }
		 
		 
	}

	@Override
	public Orders updateOrder(Orders order, String key) throws OrderException, LoginException {
		
		if( uSessionDao.findByUuid(key) != null ) {
		
			Optional<Orders> opt=  oDao.findById(order.getOrderId());
			
			if(opt.isPresent()) {
				return oDao.save(order);
			}
			else {
				throw new OrderException("Order does not exist");
			}
		}
		else {
			throw new LoginException("Please, Login First...");
		}
		
	}

	@Override
	public Orders removeOrder(Integer orderId, String key) throws OrderException {
		
		Orders	existingProduct = oDao.findById(orderId).orElseThrow(()->new OrderException("Order does not exist with id :"));
		
		oDao.delete(existingProduct);
		
		return existingProduct;
	}

	@Override
	public Orders viewOrder(Integer orderId) throws OrderException {
		Optional<Orders> opt1=  oDao.findById(orderId);
		
		if(opt1.isPresent()) {
			return opt1.get();
		}
		else {
			throw new OrderException("No order found");
		}
	}

	@Override
	public List<Orders> viewAllOrdersByDate(LocalDate date) throws OrderException {
		List<Orders> orders= oDao.findByOrderDate(date);
		
		if(orders.size()>0) {
			
			return orders;
		}
		else {
			throw new OrderException("Order doesn't exist on this date.");
		}
		
	}

	@Override
	public List<Orders> viewAllOrdersByLocation(String loc) throws OrderException, AddressException {
		
		List<Orders> list= oDao.getOrderByCity(loc);
		
		if( list.size() < 1) {
			throw new OrderException("No order found with this userId.");
		}
		return list;
	}

	@Override
	public List<Orders> viewAllOrdersByUserId(String userid) throws OrderException {

		List<Orders> list = oDao.getOrdersByUserId(userid);
		
		if( list.size() < 1) {
			throw new OrderException("No order found with this userId.");
		}
		
		return list;
	}

}

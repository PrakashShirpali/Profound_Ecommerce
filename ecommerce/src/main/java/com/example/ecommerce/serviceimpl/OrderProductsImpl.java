package com.example.ecommerce.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.ecommerce.Dao.AddressRepository;
import com.example.ecommerce.Dao.OrderProductsRepository;
import com.example.ecommerce.Dao.ProductRepository;
import com.example.ecommerce.Service.OrderProductsService;
import com.example.ecommerce.Service.ProductService;
import com.example.ecommerce.entity.Address;
import com.example.ecommerce.entity.OrderProducts;
import com.example.ecommerce.entity.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class OrderProductsImpl implements OrderProductsService {
	@Autowired
	OrderProductsRepository orderProductRepository;
	@Autowired
	AddressRepository addressRepository;

	@Override
	public String saveUserWithAddress(User user) {
		return null;
	}

	/*
	 * @Override public OrderProducts saveOrder(OrderProducts order) { boolean
	 * exists =
	 * orderProductRepository.existsByOrderIdAndStatusType(order.getOrderId(),
	 * "PENDING"); if(exists) { OrderProducts orderProducts2 =
	 * orderProductRepository.findByOrderIdAndStatusType(order.getOrderId(),
	 * "PENDING");
	 * 
	 * order.setUserId(orderProducts2.getUserId());
	 * order.setOrderId(orderProducts2.getOrderId());
	 * 
	 * order.setAddress(orderProducts2.getAddress());
	 * order.setListOfProducts(orderProducts2.getListOfProducts());
	 * order.setOrderDate(orderProducts2.getOrderDate());
	 * order.setStatusType(orderProducts2.getStatusType());
	 * order.setTotalPrice(order.getTotalPrice()+orderProducts2.getTotalPrice());
	 * OrderProducts orderProduct = orderProductRepository.save(order); return
	 * orderProduct; } else { OrderProducts orderProduct =
	 * orderProductRepository.save(order); return orderProduct; } }
	 */
	
	@Override
	public OrderProducts saveOrder(OrderProducts order) {
	    boolean exists = orderProductRepository.existsByOrderIdAndStatusType(order.getOrderId(), "PENDING");
	    if (exists) {
	        OrderProducts existingOrder = orderProductRepository.findByOrderIdAndStatusType(order.getOrderId(), "PENDING");
	        existingOrder.setAddress(order.getAddress());
	        existingOrder.setTotalPrice(existingOrder.getTotalPrice() + order.getTotalPrice());
	        return orderProductRepository.save(existingOrder);
	    } 
	    else {
	        return orderProductRepository.save(order);
	    }
	}
	
	public List<OrderProducts> findByAll(@ModelAttribute Address address,HttpServletRequest request)
	{
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		addressRepository.findByUserId(user.getUserID());
		List<OrderProducts> all = orderProductRepository.findAll();
		
		return all;
	}

	@Override
	public List<OrderProducts> getAllPendingOrderds(int id, String statusType) {
		List<OrderProducts> orders = orderProductRepository.findByUserIdAndStatusType(id, statusType);
		return orders;
	}

}

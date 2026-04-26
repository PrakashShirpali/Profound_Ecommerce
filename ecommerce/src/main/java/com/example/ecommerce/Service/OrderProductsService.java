package com.example.ecommerce.Service;

import java.util.List;

import com.example.ecommerce.entity.OrderProducts;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.User;

public interface OrderProductsService {
	String saveUserWithAddress(User user);
	OrderProducts saveOrder(OrderProducts order);
	
	List<OrderProducts> getAllPendingOrderds(int id,String statusType);
}


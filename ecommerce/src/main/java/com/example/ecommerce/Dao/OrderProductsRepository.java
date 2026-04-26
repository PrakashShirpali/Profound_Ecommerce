package com.example.ecommerce.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entity.OrderProducts;
import com.example.ecommerce.entity.User;

public interface OrderProductsRepository extends JpaRepository<OrderProducts, Integer>{

	boolean existsByUserIdAndStatusType(int id, String status);
	
	List<OrderProducts> findByUserIdAndStatusType(int id,String status);

	boolean existsByOrderIdAndStatusType(int orderId, String string);

	OrderProducts findByOrderIdAndStatusType(int orderId, String string);
	void save(User user);
}

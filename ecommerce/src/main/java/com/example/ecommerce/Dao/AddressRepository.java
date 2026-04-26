package com.example.ecommerce.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ecommerce.entity.Address;
import com.example.ecommerce.entity.OrderProducts;
import com.example.ecommerce.entity.Payment;
import com.example.ecommerce.entity.User;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>{

	void save(Payment payment);

	Address findByUserId(int userID);
	
}

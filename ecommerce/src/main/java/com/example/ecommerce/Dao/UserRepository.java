package com.example.ecommerce.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.example.ecommerce.entity.User;

@Service
public interface UserRepository extends JpaRepository<User, Integer> {
	
	boolean existsByEmailAndPassword(String email, String password);
	User findByEmail(String email);
	boolean existsByPhoneNumberAndPassword(String mobile, String password);
	User findByPhoneNumber(String phoneNumber);
	boolean existsByEmail(String email);
	User findByUserID(int id);
	User findByUsername(String username);
}

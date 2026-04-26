package com.example.ecommerce.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.entity.Address;
import com.example.ecommerce.entity.BankProcessingDetails;

public interface BankProcessingDetailsRepository extends JpaRepository<BankProcessingDetails, Integer> {
	BankProcessingDetails findByUserId(int id);
}

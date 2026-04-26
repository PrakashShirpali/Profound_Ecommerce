package com.example.ecommerce.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.Dao.BankProcessingDetailsRepository;
import com.example.ecommerce.Service.BankProcessingDetailsService;
import com.example.ecommerce.entity.BankProcessingDetails;

import jakarta.persistence.Transient;
import jakarta.transaction.Transactional;

@Service
public class BankProcessingDetailsImpl implements BankProcessingDetailsService {

	
	@Autowired
	BankProcessingDetailsRepository bankProcessDetailsRepo;
	
	@Transactional
	@Override
	public Object saveBankProcessingDetails(BankProcessingDetails details) {
		BankProcessingDetails bankDetails1 = bankProcessDetailsRepo.findByUserId(details.getUserId());
		if(bankDetails1!=null)
		{
			details.setId(bankDetails1.getId());
		}
		BankProcessingDetails bankDeatails = bankProcessDetailsRepo.save(details);

		return bankDeatails;
	}

}

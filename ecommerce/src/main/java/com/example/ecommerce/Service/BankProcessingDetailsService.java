package com.example.ecommerce.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.ecommerce.Dao.CartRepository;
import com.example.ecommerce.entity.BankProcessingDetails;

public interface BankProcessingDetailsService {
	Object saveBankProcessingDetails(BankProcessingDetails details);

}

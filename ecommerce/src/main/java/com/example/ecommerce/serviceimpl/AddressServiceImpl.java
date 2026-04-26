package com.example.ecommerce.serviceimpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.Dao.AddressRepository;
import com.example.ecommerce.Service.AddressService;
import com.example.ecommerce.entity.Address;
import com.example.ecommerce.entity.OrderProducts;
import com.example.ecommerce.entity.Payment;
import com.example.ecommerce.entity.User;

@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	AddressRepository addressRepository;
	
	@Override
	public void savePayment(Payment payment) {
		addressRepository.save(payment);
	}
	
	public Address getAddressByUserId(int userID) {
		Address address = addressRepository.findByUserId(userID);
		return address;
	}
}

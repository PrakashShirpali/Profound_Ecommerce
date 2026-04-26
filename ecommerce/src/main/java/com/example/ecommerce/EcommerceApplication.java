package com.example.ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.ecommerce.entity.User;
import com.example.ecommerce.serviceimpl.UserServiceImpl;

@SpringBootApplication
public class EcommerceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
		System.out.println("application ready");
		EcommerceApplication ecommerce=new EcommerceApplication();
	}
}

package com.example.ecommerce.controller;

import java.util.List;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.Service.ProductService;
import com.example.ecommerce.entity.BankProcessingDetails;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.response.ResponseHandler;
import com.example.ecommerce.serviceimpl.BankProcessingDetailsImpl;

import jakarta.servlet.http.HttpSession;

@RestController
@CrossOrigin("http://localhost:3000/")
public class BankProcessingDetailsController {
	
	@Autowired
	ProductService productService;
	@Autowired
	BankProcessingDetailsImpl bankProcessingDetailsImpl;
	
	@PostMapping("saveBankProcessingDetails")
	public ResponseEntity<ResponseHandler> saveBankProcessingDetails(@RequestBody BankProcessingDetails bankProcessingDetails,HttpSession session)
	{
		
		User user =(User)session.getAttribute("user");
		bankProcessingDetails.setUserId(user.getUserID());
		ResponseHandler responseHandler = new ResponseHandler();
		responseHandler.setStatusCode("200");
		responseHandler.setResponseDescription("Payment processing details saved successfully");
		responseHandler.setData(bankProcessingDetails);
		
		System.out.println("bankProcessingDetails"+bankProcessingDetails);
		Object saveBankProcessingDetails = bankProcessingDetailsImpl.saveBankProcessingDetails(bankProcessingDetails);
		return ResponseEntity.ok( responseHandler);
	}
	
	@GetMapping("getAllProducts")
	public ResponseEntity<ResponseHandler> getAllProducts()
	{
		ResponseHandler responseHandler = new ResponseHandler();
		responseHandler.setStatusCode("200");
		responseHandler.setResponseDescription("Payment processing details saved successfully");
		List<Product> products = productService.findByAll();
		responseHandler.setData(products);
		System.out.println("products"+products);
		System.out.println("responsehandler"+responseHandler);
		return ResponseEntity.ok(responseHandler);	
	}
}

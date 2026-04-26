package com.example.ecommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;

//BankDetails.java
@Entity
@Data
@ToString
public class BankDetails {
	@Id
	@GeneratedValue
private int bankDetailsId;
 private String upiId;
 private String bankName;
 private String accountNumber;
 private String cardNumber;
 private String cardExpiryDate;
 private String cashOnDelivery;
 
}
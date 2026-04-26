package com.example.ecommerce.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Payment {

	@Id
	@GeneratedValue
	int paymentId;
	String paymentMode;
	String upiId;
	String cardNumber;
	LocalDate cardExpiryDate;
	String bankName;
	String accountNumber;
	

	public int getPaymentId() {
		return paymentId;
	}


	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
	}


	public String getPaymentMode() {
		return paymentMode;
	}


	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}


	public String getUpiId() {
		return upiId;
	}


	public void setUpiId(String upiId) {
		this.upiId = upiId;
	}


	public String getCardNumber() {
		return cardNumber;
	}


	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}


	public LocalDate getCardExpiryDate() {
		return cardExpiryDate;
	}


	public void setCardExpiryDate(LocalDate cardExpiryDate) {
		this.cardExpiryDate = cardExpiryDate;
	}


	public String getBankName() {
		return bankName;
	}


	public void setBankName(String bankName) {
		this.bankName = bankName;
	}


	public String getAccountNumber() {
		return accountNumber;
	}


	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}


	@Override
	public String toString() {
		return "Payment [paymentId=" + paymentId + ", paymentMode=" + paymentMode + ", upiId=" + upiId + ", cardNumber="
				+ cardNumber + ", cardExpiryDate=" + cardExpiryDate + ", bankName=" + bankName + ", accountNumber="
				+ accountNumber + "]";
	}
	
}

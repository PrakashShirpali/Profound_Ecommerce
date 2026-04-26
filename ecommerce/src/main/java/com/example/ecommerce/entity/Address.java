package com.example.ecommerce.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Setter;


@Entity
public class Address {

@Id
@GeneratedValue
int addressId;
String pincode;
String city;
String state;
String country;
@Setter
int userId;

public void setUserId(int userID2) {
	this.userId=userID2;
	
}
public int getAddressId() {
	return addressId;
}
public void setAddressId(int addressId) {
	this.addressId = addressId;
}
public String getPincode() {
	return pincode;
}
public void setPincode(String pincode) {
	this.pincode = pincode;
}
public String getCity() {
	return city;
}
public void setCity(String city) {
	this.city = city;
}
public String getState() {
	return state;
}
public void setState(String state) {
	this.state = state;
}
public String getCountry() {
	return country;
}
public void setCountry(String country) {
	this.country = country;
}

public int getUserId() {
	return userId;
}
@Override
public String toString() {
	return "Address [addressId=" + addressId + ", pincode=" + pincode + ", city=" + city + ", state=" + state
			+ ", country=" + country + ", userId=" + userId + "]";
}


}

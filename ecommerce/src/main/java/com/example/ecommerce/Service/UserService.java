package com.example.ecommerce.Service;

import com.example.ecommerce.entity.User;

public interface UserService {
	
	String saveUser(User user);
	boolean checkLogin(String email, String password);
	User getUserByEmail(String email);
	String msgFailedLogin();
	boolean checkLoginByMobile(String mobile, String password);
	String generateOTP();
	void sendOTPEmail(String email,String otp);
	boolean isEmailPresent(String eamil);
	User findByUserID(int id);
	
//	String findForEmail();
}

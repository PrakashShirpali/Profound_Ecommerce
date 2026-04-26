package com.example.ecommerce.serviceimpl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Properties;
import java.util.Base64.Encoder;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.Dao.OrderProductsRepository;
import com.example.ecommerce.Dao.ProductRepository;
import com.example.ecommerce.Dao.UserRepository;
import com.example.ecommerce.Service.UserService;
import com.example.ecommerce.controller.EmailSender;
import com.example.ecommerce.controller.ForgotPasswordEmailSender;
import com.example.ecommerce.entity.User;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	OrderProductsRepository orderProductsRepository;
	
	private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";
    private static final String USERNAME = "ashitoshnevase@gmail.com"; // Replace with your email
    private static final String PASSWORD="Ash"; 
	
	  public String saveUserWithAddress(User user) {
		  Encoder encoder = Base64.getEncoder();
	  user.setPassword(encoder.encodeToString(user.getPassword().getBytes()));
	  user.setUserType("CUSTOMER");
	  orderProductsRepository.save(user);
	  EmailSender.sendSignUpEmail(user);
	  return "Congratulations your account is created successfully!"; }
	 
	
	public User getUserByEmail(String email)
	{
		User user = userRepository.findByEmail(email);
		return user;		
	}

	public String msgFailedLogin()
	{
		return "Wrong username or password";
	}
	
	

	@Override
	public boolean checkLogin(String email, String password) {
		
		  Encoder encoder = Base64.getEncoder(); 
		  String encodeToPassword = encoder.encodeToString(password.getBytes());
		 
		boolean existsByEmailAndPassword = userRepository.existsByEmailAndPassword(email, encodeToPassword);
		return existsByEmailAndPassword;
	}
	
	@Override
	public boolean checkLoginByMobile(String mobile, String password) {
		/*
		 * Encoder encoder = Base64.getEncoder(); String encodeToPassword =
		 * encoder.encodeToString(password.getBytes());
		 */
		boolean isTrue = userRepository.existsByPhoneNumberAndPassword(mobile, password);
		return isTrue;
	}


	@Override
	public String saveUser(User user) {
		Encoder encoder = Base64.getEncoder(); 
		user.setUserType("CUSTOMER");
		User user1=new User();
		user1.setUsername("ADMIN");
		user1.setUserType("ADMIN");
		user1.setEmail("admin@gmail.com");
		user1.setPhoneNumber("7744898524");
		String encodeToPassword = encoder.encodeToString(user.getPassword().getBytes());
		String encodeToPassword1 = encoder.encodeToString("admin".getBytes());
		User adminUser = userRepository.findByUsername("ADMIN");
		if(adminUser==null)
		{
			user1.setPassword(encodeToPassword1);
			userRepository.save(user1);
		}
		user.setPassword(encodeToPassword);
		User user2 = userRepository.save(user);

		return null;
	}

	public User getUserByPhoneNumber(String phoneNumber) {
		User number=userRepository.findByPhoneNumber(phoneNumber);
		return number;
	}


	public User getAddressByEmail(String email) {
		User user = userRepository.findByEmail(email);
		return null;
	}


	public String generateOTP() {
	    Random random = new Random();
	    int otpLength = 4; 
	    StringBuilder otp = new StringBuilder();
	    for (int i = 0; i < otpLength; i++) {
	        otp.append(random.nextInt(10));
	    }
	    return otp.toString();
}
	

	


	@Override
	public boolean isEmailPresent(String eamil) {
		boolean isEmailPresent = userRepository.existsByEmail(eamil);
		return isEmailPresent;
	}


	@Override
	public User findByUserID(int id) {
	User userId = userRepository.findByUserID(id);
		return userId;
	}


	@Override
	public void sendOTPEmail(String email, String otp) {
		ForgotPasswordEmailSender.sendOTPEmail(email, otp);
	}
	
	public boolean isOtpExpired(HttpSession session)
	{
		LocalDateTime otpGenarartionTime = (LocalDateTime)session.getAttribute("otpgenerationtime");
		LocalDateTime currentTime = LocalDateTime.now();
		System.out.println("current Time : "+currentTime);
		System.out.println("otp GenarartionTime : "+otpGenarartionTime);
		Duration duration = Duration.between(otpGenarartionTime, currentTime);

	    // Get the time difference in minutes
	    long minutesDifference = duration.toMinutes();

	    System.out.println("Time difference in minutes: " + minutesDifference);
		return minutesDifference>0;
		
	}
	
	
}

	

//	public String findForEmail() {
//		return "This username is allready present";
//	}	

package com.example.ecommerce.controller;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ForgotPasswordEmailSender {
	private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";
    private static final String USERNAME = "ashitoshnevase@gmail.com"; // Replace with your email
    private static final String PASSWORD="okatvkwlcukibyfw";
	
	public static void sendOTPEmail(String email, String otp) {
	    // Sender's email credentials

	    // SMTP server configuration
	    Properties props = new Properties();
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.smtp.host", SMTP_HOST);
	    props.put("mail.smtp.port", SMTP_PORT);
	    
	    // Create a session with authentication
	    Session session = Session.getInstance(props, new Authenticator() {
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(USERNAME, PASSWORD);
	        }
	       
	    });

	    try {
	        // Create a default MimeMessage object
	        MimeMessage message = new MimeMessage(session);

	        // Set From: header field of the header
	        message.setFrom(new InternetAddress(USERNAME));

	        // Set To: header field of the header
	        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));

	        // Set Subject: header field
	        message.setSubject("Password Reset OTP");

	        // Set the actual OTP message
	        message.setText("Your OTP for password reset is: " + otp);
	        // Send message
	        Transport.send(message);

	        System.out.println("Email sent successfully!");

	    } catch (MessagingException e) {
	        throw new RuntimeException(e);
	    }
	}
}
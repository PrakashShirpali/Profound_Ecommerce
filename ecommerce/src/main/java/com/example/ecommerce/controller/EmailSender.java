package com.example.ecommerce.controller;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import com.example.ecommerce.entity.User;

import jakarta.servlet.http.HttpSession;


public class EmailSender {
	private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";
    private static final String USERNAME = "ashitoshnevase@gmail.com"; // Replace with your email
    private static final String PASSWORD=null; // Replace with your password
    
    public static boolean sendSignUpEmail(User user) {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", SMTP_HOST);
        properties.put("mail.smtp.port", SMTP_PORT);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
            message.setSubject("Your account is created successfully");

            // Email body with bold OTP
            String emailBody = "Dear "+user.getUsername()+" ,<br><br>"
            		+" Your account is created successfully, on amazon.com ";
                    
            message.setContent(emailBody, "text/html"); // Set content type to HTML

            Transport.send(message);

            System.out.println("Email sent successfully to: " + user.getEmail());
            return true;
        } catch (MessagingException e) {
            // Log the exception or throw a custom exception with a meaningful message
            e.printStackTrace();
            return false;
        }
    }

}

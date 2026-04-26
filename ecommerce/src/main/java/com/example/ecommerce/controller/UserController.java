package com.example.ecommerce.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.serviceimpl.CartServiceImpl;
import com.example.ecommerce.serviceimpl.ProductServiceImpl;
import com.example.ecommerce.serviceimpl.UserServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {

	@Autowired
	ProductServiceImpl productServiceImpl;
	@Autowired
	UserServiceImpl userServiceImpl;
	@Autowired
	CartServiceImpl cartServiceImpl;

	@GetMapping("home")
	public String home(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String alert = request.getParameter("alert");
		String search = request.getParameter("search");
		String category=request.getParameter("category");
		
		System.out.println("Search value "+search);
		model.addAttribute("user", user);
		model.addAttribute("isUserLoggedIn",alert);
		/*
		 * List<Product> newProducts = productServiceImpl.findByAll();
		 */	
		String pageNoInString = request.getParameter("page");
		int pageNo=pageNoInString==null?0:Integer.parseInt(pageNoInString);
		
		Page<Product> pageContent;
		
		System.out.println("category="+category);
		model.addAttribute("category",category);
		if(category==null || category.equals("null"))
		{
		System.err.println("If block executed");
		 pageContent= productServiceImpl.findByAll(pageNo);
		}
		else
		{
			System.err.println("else block executed");
			pageContent = productServiceImpl.findByProduct(pageNo,category);
		}
		List<Product> newProducts = pageContent.getContent();
		ArrayList<Product> products = new ArrayList<>();
		Object totalQuantity=0;
		List<Cart> cards=new ArrayList<>();
		if(user!=null) {
		 cards = cartServiceImpl.findAllByUserId(user.getUserID());
		 System.out.println(cards);
		 model.addAttribute("cards",cards);
		totalQuantity = cartServiceImpl.getQuantityByProductByUserId(user.getUserID());
		}
		for (Product product : newProducts) {
			for(Cart card:cards)
			{
			if(card.getProductId()==product.getProductId())
			{
				product.setProductQuantity(card.getQuantity());
			}
			}
			Encoder encoder = Base64.getEncoder();
			byte[] image = product.getImage();
			String imagePath = encoder.encodeToString(image);
			int priceAfterDiscount;
			int discountPercentage = Integer.parseInt(product.getDiscount());
			int actualPrice = Integer.parseInt(product.getPrice());
			int discPrice = (actualPrice / 100) * discountPercentage;
			priceAfterDiscount = actualPrice - discPrice;
			product.setDiscountPrice(priceAfterDiscount);
			product.setImagePath(imagePath);
			if(search=="" || search==null)
			{
			products.add(product);
			}
			else
			{
				String data=product.getProductName() +" "+product.getPrice()+" "+product.getProductId()+" "+product.getDiscount()+" "+product.getProductQuantity() ;
				
				if(data.toLowerCase().contains(search.toLowerCase()))
				{
					products.add(product);
				}
			}
			System.err.print(product);
		}
		
		
		
		System.out.print(products);
		model.addAttribute("products", products);
		model.addAttribute("totalQuantity",totalQuantity);
		model.addAttribute("pageContent",pageContent);
		return "home";
	}

	/*
	 * @GetMapping("showProduct") public void show(@ModelAttribute Product
	 * product,HttpServletRequest request,Model model) { HttpSession session =
	 * request.getSession(); product = (Product)session.getAttribute("product");
	 * model.addAttribute("user",product); Encoder encoder = Base64.getEncoder();
	 * byte[] image = product.getImage(); String imagePath =
	 * encoder.encodeToString(image); List<Product> product1 =
	 * productServiceImpl.findByImagePath(imagePath); ArrayList<Product> products =
	 * new ArrayList<>(); for(Product obj: product1) { Encoder encoder1 =
	 * Base64.getEncoder(); byte[] image1 = obj.getImage(); String imagePath1 =
	 * encoder1.encodeToString(image1); int priceAfterDiscount; int
	 * discountPercentage=Integer.parseInt(obj.getDiscount()); int
	 * actualPrice=Integer.parseInt(obj.getPrice()); int
	 * discPrice=(actualPrice/100)*discountPercentage;
	 * priceAfterDiscount=actualPrice-discPrice;
	 * obj.setDiscountPrice(priceAfterDiscount); obj.setImagePath(imagePath1);
	 * products.add(obj); System.err.print(obj); }
	 * model.addAttribute("products",products); }
	 */
	

	@GetMapping("login")
	public String login(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user != null) {
			return "redirect:/home";
		}
		/*
		 * else if(user==null) { return "login"; }
		 */
		System.out.println("user : " + user);

		return "login";
	}

	@GetMapping("loginbymobile")
	public String loginByMobile(HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user != null) {
			return "redirect:/home";
		}
		/*
		 * else if(user==null) { return "login"; }
		 */
		System.out.println("user : " + user);

		return "loginbymobile";
	}
	
	@GetMapping("signup")
	public String signup() {
		return "signup";
	}

	@PostMapping("loginUser")
	public String loginUser(@ModelAttribute User user, HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		System.out.println("user : " + user);
		boolean istrue = userServiceImpl.checkLogin(user.getEmail(), user.getPassword());
		boolean istrueMobile=userServiceImpl.checkLoginByMobile(user.getEmail(), user.getPassword());
		System.out.println("isTrue"+istrue);
		System.out.println("isTrueMobile"+istrueMobile);

//		System.out.println(userServiceImpl.checkLogin(user.getEmail(),user.getPassword()));
		System.out.println("---------- ---------------------------" + user);
		if (istrue) {
			System.out.println("user login with emial");
			user = userServiceImpl.getUserByEmail(user.getEmail());
			System.err.println("login user: " + user);
			session.setAttribute("user", user);
			return "redirect:/home";
		}
		else if(istrueMobile)
		{
			System.out.println("user login with mobile");

			user = userServiceImpl.getUserByPhoneNumber(user.getEmail());
			System.err.println("login user: " + user);
			session.setAttribute("user", user);
			return "redirect:/home";
		}
		else {
			String msg = userServiceImpl.msgFailedLogin();
			model.addAttribute("msgFailedLogin", msg);
			return "login";
		}
	}
	

	@PostMapping("loginUserByMobile")
	public String loginUserByMobile(@ModelAttribute User user, HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		System.out.println("user : " + user);
		boolean istrueMobile=userServiceImpl.checkLoginByMobile(user.getPhoneNumber(), user.getPassword());
//		System.out.println(userServiceImpl.checkLogin(user.getEmail(),user.getPassword()));
		System.out.println("---------- ---------------------------" + user);
		if (istrueMobile == true) {
			user = userServiceImpl.getUserByEmail(user.getEmail());
			System.err.println("login user: " + user);
			session.setAttribute("user", user);
			return "redirect:/home";
		}	
		else {
			String msg = userServiceImpl.msgFailedLogin();
			model.addAttribute("msgFailedLogin", msg);
			return "login";
		}
	}

	@PostMapping("signUpUser")
	public String signUpUser(@ModelAttribute User user, Model model) {

		User isTrue = userServiceImpl.getUserByEmail(user.getEmail());
		if (isTrue == null) {
			
			String msg = userServiceImpl.saveUser(user);
			model.addAttribute("msg", "User registered Successfully");
			System.out.println(user);
			System.out.println(msg);
		} else {
//			String msg=userServiceImpl.findForEmail();
			model.addAttribute("msg", "User not registered Successfully");
		}
		return "signup";
	}
	
	
	
	
	@GetMapping("logout")
	public String logout(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		return "redirect:/home";
	}
	
	@PostMapping("sendotp")
	public String forgetPassword(Model model, HttpServletRequest request) {
	    String email = request.getParameter("email");
	    HttpSession session = request.getSession();
	    if(email!=null)
	    {
	    session.setAttribute("email", email);
	    }
   
	    if(email==null)
	    {
	    	email=(String)session.getAttribute("email");
	    }
	    
	    boolean isEmailPresent = userServiceImpl.isEmailPresent(email);
	    System.err.println(isEmailPresent);
	    if(isEmailPresent) {
	    // Generate OTP
	    String otp = userServiceImpl.generateOTP();
	    session.setAttribute("otpgenerationtime", LocalDateTime.now());
	    System.err.println(otp);
	    
	    // Store the OTP temporarily, associating it with the email address (e.g., in a database)
	   
	    session.setAttribute("otp", otp);
	    session.setAttribute("email", email);
	    String otp1 =(String) session.getAttribute("otp");
	    System.err.println(otp1); 
	    
	    // Send OTP to email 
	    
	    userServiceImpl.sendOTPEmail(email, otp);
	    model.addAttribute("email", email);

	    System.err.println("email= " + email);
	    
	    LocalDateTime localDateTime = (LocalDateTime) session.getAttribute("otpgenerationtime");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		if(localDateTime!=null) {
			String formattedDateTime = localDateTime.format(formatter);
			model.addAttribute("formattedDateTime", formattedDateTime);
		}
		
	    return "enterotp";
	    }
	    else
	    {
	    	model.addAttribute("error","User is not present. Please signup!");
	    	return "login";
	    }    
	}
	
	@PostMapping("isotpcorrect")
	public String isOtpCorrect(HttpServletRequest request,Model model)
	{
		HttpSession session = request.getSession();
		boolean isOtpExpired = userServiceImpl.isOtpExpired(session);
		System.out.println("is otp expired : "+isOtpExpired);
		String otp =(String) session.getAttribute("otp");
		String otp1 = request.getParameter("otp");
		if(isOtpExpired)
		{
			LocalDateTime localDateTime = (LocalDateTime) session.getAttribute("otpgenerationtime");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
			if(localDateTime!=null) {
				String formattedDateTime = localDateTime.format(formatter);
				model.addAttribute("formattedDateTime", formattedDateTime);
			}
			model.addAttribute("msg","Otp is expierd, please click to resend otp");
			return "enterotp"; 
		}
		else if(otp.equals(otp1))
		{
			return "changepassword";
		}
		else
		{
			LocalDateTime localDateTime = (LocalDateTime) session.getAttribute("otpgenerationtime");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
			if(localDateTime!=null) {
				String formattedDateTime = localDateTime.format(formatter);
				model.addAttribute("formattedDateTime", formattedDateTime);
			}
			model.addAttribute("msg","Otp is not matching");
			return "enterotp";
		}
	}
	
	@PostMapping("changepassword")
	public String changePassword(HttpServletRequest request,Model model)
	{
		HttpSession session = request.getSession();
		String email =(String) session.getAttribute("email");
		User user = userServiceImpl.getUserByEmail(email);
		String pass1 = request.getParameter("password");
		String pass2 = request.getParameter("password1");
		if(pass1.equals(pass2))
		{
			user.setPassword(pass1);
			userServiceImpl.saveUser(user);
			model.addAttribute("msg","Password changed successfully!");
			return "changepassword";
		}
		else
		{
			model.addAttribute("msg","Password mismatch");
			return "changepassword";
		}
	
		
	}
	
}

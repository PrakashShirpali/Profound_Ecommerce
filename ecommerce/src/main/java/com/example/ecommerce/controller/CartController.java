package com.example.ecommerce.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Base64.Encoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.serviceimpl.CartServiceImpl;
import com.example.ecommerce.serviceimpl.ProductServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CartController {

	@Autowired
	CartServiceImpl cartServiceimpl;
	@Autowired
	ProductServiceImpl productServiceImpl;
	
	 @PostMapping("addToCart") 
	 public String addToCart(@RequestParam(name = "id") int productId,Model model, HttpServletRequest request)
	  {
		 HttpSession session = request.getSession();
		 User user = (User)session.getAttribute("user");
		 String category = request.getParameter("category");
		 String pagename = request.getParameter("pagename");
		 System.out.println("id: "+productId);
		  int id = productId;
//			 System.out.println("id : "+id);
//			 model.addAttribute("id",id);
			 Product pid = productServiceImpl.getProductById(id);
		 Cart cart=new Cart();
			/*
			 * boolean isTrue = cartServiceImpl.checkId(id);
			 */	
		 if(user!=null) {
		 boolean isTrue = cartServiceimpl.checkUserIdAndProductId(user.getUserID(), productId);
		 
		if(isTrue==true)
		 {
				/* Cart cart1 = cartServiceImpl.getById(productId); */
			Cart cart1 = cartServiceimpl.getByProductIdAndUSerId(user.getUserID(), productId);	        
			cart1.quantity=cart1.getQuantity()+1;
			cart1.totalPrice=cart1.getPrice()*cart1.quantity;
			int discPrice = (cart1.getPrice() / 100) * cart1.getDiscount();
			int priceAfterDiscount = cart1.getPrice() - discPrice;
			int totalPrice=priceAfterDiscount*cart1.getQuantity();
			cart1.finalPrice=totalPrice;
			 cartServiceimpl.saveProductInCart(cart1);
		 }
		else
		{
		 cart.quantity=1;
		 cart.productId=pid.getProductId();
		 cart.price=Integer.parseInt(pid.getPrice());
		 cart.totalPrice=cart.price*cart.quantity;
		 cart.image=pid.getImage();
		 cart.imagePath=pid.getImagePath();
		 cart.productName=pid.getProductName();
		 cart.discount=Integer.parseInt(pid.getDiscount());
		 cart.date=LocalDate.now();
		 cart.userId=user.getUserID();
		 int discPrice = (cart.getPrice() / 100) * cart.getDiscount();
			int priceAfterDiscount = cart.getPrice() - discPrice;
			int totalPrice=priceAfterDiscount*cart.getQuantity();
			cart.finalPrice=totalPrice;
		 cartServiceimpl.saveProductInCart(cart);		 
	  }
		 }
		 else
		 {
			 return "redirect:/home?alert=false";
		 }
		 
		 
		 if(pagename!=null &&pagename.equals("showCart"))
		 {
			 return "redirect:/showCart";
		 }
			 return "redirect:/home?category="+category;	
		 
		 
	  }
	 
	 @GetMapping("showCart")
	 public String showProductInCart(HttpServletRequest request,Model model) {
		 HttpSession session = request.getSession();
		  User user = (User)session.getAttribute("user");
		  if (user == null) {
		        return "redirect:/login";
		    }
		  
		  int userId=user.getUserID();
		  System.out.println(userId);

	  List<Cart> newProducts = cartServiceimpl.findAllByUserId(userId);
	  System.out.println(newProducts);
	  ArrayList<Cart>products = new ArrayList<>();
	  int finalPrice=0;
	  for (Cart cart : newProducts) 
	  {
			
			/*
			 * HttpSession session = request.getSession(); User user =
			 * (User)session.getAttribute("user");
			 */
	  	 
	  Encoder encoder = Base64.getEncoder();
	  byte[]image = cart.getImage();
	  String imagePath = encoder.encodeToString(image);
		/*
		 * int priceAfterDiscaount =cart.getPrice(); int totalPrice =
		 * cart.getPrice()*cart.getQuantity();
		 */
		finalPrice = cartServiceimpl.getFinalPriceByUserId(user.getUserID());

	  int priceAfterDiscount;
		int discountPercentage = cart.getDiscount();
		int actualPrice = cart.getPrice();
		int discPrice = (actualPrice / 100) * discountPercentage;
		priceAfterDiscount = actualPrice - discPrice;
		int totalPrice=priceAfterDiscount*cart.getQuantity();
	  cart.setImagePath(imagePath);
	  cart.setPrice(actualPrice);
	  cart.setTotalPrice(totalPrice);
	  cart.userId=user.getUserID();
	  cart.productName=cart.getProductName();
	  products.add(cart);
	  System.err.print(cart);
	  }
	  // System.out.print(products);
	  model.addAttribute("user", user);
	  model.addAttribute("products", products);
		model.addAttribute("finalPrice",finalPrice);

	  return "showCart";
	  }	
	 
	 @PostMapping("removeFromCart")
	 public String removeFromCart(HttpServletRequest request,HttpSession session)
	 {
		 String pagename = request.getParameter("pagename");
		 String category = request.getParameter("category");
		 String productId = request.getParameter("id");
		 int pid=Integer.parseInt(productId);
		 User user = (User)session.getAttribute("user");
		 int userID = user.getUserID();
		 System.out.println("userId"+userID);
		 System.out.println("productId"+productId);
		 String removeFromCart  =cartServiceimpl.removeFromCart(userID,pid);
		 if(pagename!=null &&pagename.equals("showCart"))
		 {
			 return "redirect:/showCart";
		 }
		 return "redirect:/home?category="+category;
	 }
	 
	
}

package com.example.ecommerce.controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Base64.Encoder;
import java.util.Optional;

import org.hibernate.type.descriptor.java.LocalDateTimeJavaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.ecommerce.entity.Address;
import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.serviceimpl.ProductServiceImpl;
import com.example.ecommerce.serviceimpl.UserServiceImpl;
import com.example.ecommerce.serviceimpl.CartServiceImpl;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping
public class ProductController {

	@Autowired
	ProductServiceImpl productServiceImpl;
	@Autowired
	CartServiceImpl cartServiceImpl;

	@GetMapping("product")
	public String product(HttpServletRequest request ) {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user==null)
		{
			return "redirect:/login";
		}
		System.out.println("user : "+user);
		return "product";
	}
	
	
	
	@PostMapping("productRegister")
	public String signUpUser(@ModelAttribute Product product, Model model,MultipartFile productImage) throws IOException {
		
		product.setImage(productImage.getBytes());
		String msg = productServiceImpl.saveProduct(product);
		model.addAttribute("pmsg",msg);
//		System.out.println(product);
//		System.out.println(msg);
		return "product";
	}
	
	@GetMapping("showAll")
	public String home( ) {

		return "showAll";
	}

	
	@GetMapping("showProduct/{id}")
	public String showProduct(@PathVariable("id") int productId, Model model)
	{
		Product product = productServiceImpl.getProductById(productId);
		Encoder encoder = Base64.getEncoder();
		byte[] image = product.getImage();
		String imagePath = encoder.encodeToString(image);
		product.setImagePath(imagePath);
//		System.out.println("id : "+product);
		model.addAttribute("obj",product);
		return "showProduct";		
	}
	
	
	
		
			 @GetMapping("updateToCart/{productId}")
			 public String getProductDetails(@PathVariable String productId, Model model) {
				 int id=Integer.parseInt(productId);
				 boolean productInCart = cartServiceImpl.existsByProductId(id);
			  System.err.println("is Present: "+productInCart);
			  model.addAttribute("productInCart", productInCart);
			  return "redirect:/home";
			  }
			 
			 
			 @PostMapping("deleteProduct")
			 public String deleteProduct(HttpServletRequest request,@RequestParam(name = "id") int productId)
			 {
				 HttpSession session = request.getSession();
				 User user = (User)session.getAttribute("user");
				 int id = productId;
				 productServiceImpl.deleteProduct(id);
				 return "redirect:/home";
			 }
			 
			 @GetMapping("updateProduct")
			 public String updateProduct(HttpServletRequest request,@RequestParam(name = "id") int productId,Model model)
			 {
				 HttpSession session = request.getSession();
					User user = (User) session.getAttribute("user");
					Product product = productServiceImpl.getProductById(productId);
					model.addAttribute("product", product);
					return "updateProduct";
			 }
			 
			 @PostMapping("registerUpdatedProduct")
			 public String registerUpdatedProduct(@ModelAttribute Product product, HttpServletRequest request, Model model) throws IOException {
			     HttpSession session = request.getSession();
			     User user = (User) session.getAttribute("user");
			     String msg = productServiceImpl.updateProduct(product.getProductName(), product.getPrice(), product.getCategory(), product.getDiscount(), product.getProductId());
			     model.addAttribute("pmsg", msg);
			     model.addAttribute("product", product);
			     return "updateProduct";
			 }

			 
			 @GetMapping("showProductByCategory")
			 public String showProductByCategory(HttpServletRequest request,@RequestParam(name = "category") String category, Model model) {
				 HttpSession session = request.getSession();
					User user = (User) session.getAttribute("user");
					String alert = request.getParameter("alert");
					String search = request.getParameter("search");
					System.out.println("Search value "+search);
					model.addAttribute("user", user);
					model.addAttribute("isUserLoggedIn",alert);
					/*
					 * List<Product> newProducts = productServiceImpl.findByAll();
					 */	
					String pageNoInString = request.getParameter("page");
					int pageNo=pageNoInString==null?0:Integer.parseInt(pageNoInString);
					
					Page<Product> pageContent = productServiceImpl.findByProduct(pageNo,category);
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
			 	
			 
					}
					 model.addAttribute("products", products);
						model.addAttribute("totalQuantity",totalQuantity);
						model.addAttribute("pageContent",pageContent);
						return "showProductByCategory";
			 }			
}


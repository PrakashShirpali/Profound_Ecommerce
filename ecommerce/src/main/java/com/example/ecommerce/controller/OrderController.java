package com.example.ecommerce.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.ecommerce.Dao.AddressRepository;
import com.example.ecommerce.Dao.OrderProductsRepository;
import com.example.ecommerce.Service.AddressService;
import com.example.ecommerce.Service.OrderProductsService;
import com.example.ecommerce.entity.Address;
import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.entity.OrderProducts;
import com.example.ecommerce.entity.Payment;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.entity.User;
import com.example.ecommerce.serviceimpl.AddressServiceImpl;
import com.example.ecommerce.serviceimpl.CartServiceImpl;
import com.example.ecommerce.serviceimpl.OrderProductsImpl;
import com.example.ecommerce.serviceimpl.ProductServiceImpl;
import com.example.ecommerce.serviceimpl.UserServiceImpl;

import constants.MessageConstant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class OrderController {

	@Autowired
	AddressRepository addressRepository;
	@Autowired
	UserServiceImpl userServiceImpl;
	@Autowired
	CartServiceImpl cartServiceImpl;
	@Autowired
	OrderProductsRepository orderProductrepo;
	@Autowired
	AddressServiceImpl addressServiceImpl;
	@Autowired
	ProductServiceImpl productServiceImpl;
	@Autowired
	OrderProductsImpl orderProductsImpl; 

	@GetMapping("orderNow")
	public String orderNow(HttpServletRequest request, Model model) {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		System.out.println("Login User: " + user);
		user = userServiceImpl.getUserByEmail(user.getEmail());
		session.setAttribute("user", user);
		System.out.println("Updated user: " + user);
		 List<OrderProducts> orders = orderProductsImpl.getAllPendingOrderds(user.getUserID(), MessageConstant.PENDING);
		model.addAttribute("user", user);
		model.addAttribute("orders", orders);
		model.addAttribute("user", user);
		System.err.println("user"+user);
		
		return "order";

	}

	@GetMapping("order")
	public String order(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		Address address = addressServiceImpl.getAddressByUserId(user.getUserID());
		System.out.println("Address by email" + address);
        List<OrderProducts> orders = orderProductsImpl.getAllPendingOrderds(user.getUserID(), MessageConstant.PENDING);
		model.addAttribute("adress", address);
		model.addAttribute("user", user);
		model.addAttribute("orders", orders);
		return "order";

	}

	@PostMapping("saveAddress")
	public String saveAddress(@ModelAttribute Address address, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		address.setUserId(user.getUserID());
		addressRepository.save(address);
		return "redirect:/orderNow";
	}

	@PostMapping("savePayment")
	public String savePayment(@ModelAttribute Payment payment, HttpServletRequest request) {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		payment.setPaymentId(user.getUserID());
		addressServiceImpl.savePayment(payment);
		return "redirect:/orderNow";
	}

	/*
	 * @PostMapping("saveAddress") public String savePayment(@ModelAttribute Address
	 * payment,HttpServletRequest request) { HttpSession session =
	 * request.getSession(); User user = (User)session.getAttribute("user");
	 * payment.setUserId(user.getUserID()); addressRepository.save(payment); return
	 * "redirect:/orderNow"; }
	 */

	@GetMapping("checkOrder")
	String showOrder(HttpServletRequest request) {
		OrderProducts order = new OrderProducts();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		order.setUserId(user.getUserID());
		order.setOrderDate(LocalDate.now());
		List<Cart> carts = cartServiceImpl.findAllByUserId(user.getUserID());
		System.out.println(carts);
		ArrayList<Product> products = new ArrayList<>();
		order.setTotalPrice(0);
		order.setStatusType("PENDING");
		/* order.setListOfProducts(newProducts); */
		int finalPrice = cartServiceImpl.getFinalPriceByUserId(user.getUserID());
		order.setTotalPrice(finalPrice);

		for (Cart cart : carts) {
			
			Product product = productServiceImpl.getById(cart.getProductId());
			product.setProductQuantity(cart.getQuantity());
			products.add(product);
		}
		order.setListOfProducts(products);
		OrderProducts orderProducts = orderProductsImpl.saveOrder(order);
		System.err.println("Order products "+orderProducts);
		return "redirect:/showCart";
	}

	@GetMapping("allOrders")
    public String findAllProducts(Model model, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        // Fetch the Address object associated with the user
        Address userAddress = addressRepository.findByUserId(user.getUserID());

        // Fetch all products for the user
        List<OrderProducts> orders = orderProductsImpl.findByAll(userAddress, request);

        // Add the address and products to the model
        model.addAttribute("address", userAddress);
        model.addAttribute("orders", orders);

        // Return the view name
        return "order";
    }
	/*
	 * @PostMapping("saveAddress") public String saveAddress(HttpServletRequest
	 * request) { Address address=new Address(); String pincode =
	 * request.getParameter("pincode"); address.setPincode(pincode);
	 * addressRepository.save(address); return "redirect:/orderNow"; }
	 */

}

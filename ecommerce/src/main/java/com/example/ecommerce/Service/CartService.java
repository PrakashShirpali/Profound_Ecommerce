package com.example.ecommerce.Service;

import java.util.List;

import com.example.ecommerce.entity.Cart;

public interface CartService {
	boolean checkId(int id);
	String saveProductInCart(Cart cart);
	Cart getById(int id);
	boolean checkUserIdAndProductId(int userId,int productId);
	Cart getByProductIdAndUSerId(int userId, int productId);
	Object getQuantityByProductByUserId(int id);
	List<Cart> findAllByUserId(int id);
	boolean existsByProductId(int productId);
	String removeFromCart(int userId,int productId);
    int getFinalPriceByUserId(int userID);
}

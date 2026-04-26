package com.example.ecommerce.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.Dao.CartRepository;
import com.example.ecommerce.Dao.ProductRepository;
import com.example.ecommerce.Dao.UserRepository;
import com.example.ecommerce.Service.CartService;
import com.example.ecommerce.entity.Cart;
import com.example.ecommerce.entity.Product;

import jakarta.transaction.Transactional;

@Service
public class CartServiceImpl implements CartService {

	@Autowired
	CartRepository cartRepository;

	@Override
	public boolean checkId(int id) {
		boolean isTrue = cartRepository.existsByProductId(id);
		return isTrue;
	}

	@Override
	public String saveProductInCart(Cart cart) {
		cartRepository.save(cart);
		return "Success";
	}

	@Override
	public Cart getById(int id) {
		 List<Cart> byUserId = cartRepository.findByUserId(id);
		Cart cart=byUserId.get(id);
		return cart;
	}

	public List<Cart> findByAll() {
		List<Cart> list = cartRepository.findAll();
		return list;
	}

	@Override
	public boolean checkUserIdAndProductId(int userId, int productId) {
	boolean existsByUserIdAndProductId = cartRepository.existsByUserIdAndProductId(userId, productId);
		return existsByUserIdAndProductId;
	}

	@Override
	public Cart getByProductIdAndUSerId(int userId, int productId) {
		Cart byUserIdAndProductId = cartRepository.findByUserIdAndProductId(userId, productId);
		return byUserIdAndProductId;
	}

	/*
	 * @Override public Object getTotalPriceByUserId(int id) { Object quantitySum =
	 * cartRepository.findTotalPriceSum(id); return quantitySum; }
	 */

	@Override
	public List<Cart> findAllByUserId(int id) {
		List<Cart> list = cartRepository.findByUserId(id);
		return list;
	}


	@Override
	public boolean existsByProductId(int productId) {
		boolean existsByProductId = cartRepository.existsByProductId(productId);
		return existsByProductId;
	}

	@Override
	@Transactional
	public String removeFromCart(int userId, int productId) {
		
		Cart product = cartRepository.findByUserIdAndProductId(userId, productId);
		if(product.getQuantity()==1)
		{
			cartRepository.deleteByUserIdAndProductId(userId, productId);
		}
		else {
		product.setQuantity(product.getQuantity()-1);
		cartRepository.save(product);
		}
		return "Product removed";
	}

	public int getFinalPriceByUserId(int userID) {
		int quantitySum = cartRepository.findFinalPriceSum(userID);
		return quantitySum;
	}
	
	@Override
	public Object getQuantityByProductByUserId(int id) {
		Object quantitySum = cartRepository.findQuantitySum(id);
		return quantitySum;
	}

}

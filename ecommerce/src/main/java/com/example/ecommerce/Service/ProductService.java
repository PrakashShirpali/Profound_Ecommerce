package com.example.ecommerce.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

import com.example.ecommerce.entity.Product;

public interface ProductService {

	String saveProduct(Product product);

	List<Product> findByImagePath(String string);

	List<Product> findByAll();
	
	Product getProductById(int id);

	Product getById(int id);
	
	Page<Product> findByAll(int pageno);

	void deleteProduct(int id);
	

	String updateProduct(String productName, String price, String category,String discount,int id) ;
	
	Page<Product> findByProduct(int page,String category);

	Page<Product> findByProductName(String name, int pageno);
}

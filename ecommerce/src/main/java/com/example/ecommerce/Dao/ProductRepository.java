package com.example.ecommerce.Dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.ecommerce.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>, PagingAndSortingRepository<Product, Integer>{

	List<Product> findByImagePath(String string);

	Optional<Product> findById(int id);
	
	  @Transactional
	    @Modifying
	    @Query("UPDATE Product p SET p.productName = :productName, p.price = :price, p.category = :category, p.discount = :discount WHERE p.id = :productId")
	    void updateProduct(@Param("productName") String productName, 
	                       @Param("price") String price, 
	                       @Param("category") String category, 
	                       @Param("discount") String discount, 
	                       @Param("productId") int productId);
	  
	  Page<Product> findByCategory(PageRequest pageRequest,String category);
}


package com.example.ecommerce.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.example.ecommerce.entity.Cart;

@Service
public interface CartRepository extends JpaRepository<Cart, Integer> {
	boolean existsByProductId(int id);
	Cart findByProductId(int id);
	boolean existsByUserIdAndProductId(int userId,int productId);
	Cart findByUserIdAndProductId(int userId, int productId);
	@Query("select sum(quantity) from Cart where userId = :userId")
	Object findQuantitySum(@Param(value = "userId") int userId);
	List<Cart> findByUserId(int id);
	
	/*
	 * @Query("select * from Cart where userId= :userId") List<Cart>
	 * findAllById(@Param(value ="userId")int userID);
	 */
	
	int deleteByUserIdAndProductId(int userId,int productId);
	@Query("select sum(finalPrice) from Cart where userId = :userId")
	int findFinalPriceSum(@Param(value = "userId") int userID);
}
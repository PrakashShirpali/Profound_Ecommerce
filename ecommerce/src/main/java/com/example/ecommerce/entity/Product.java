package com.example.ecommerce.entity;

import java.util.Arrays;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
public class Product {
	
	@Id
	@GeneratedValue
	int productId;
	String productName;
	String price;
	String category;
	String discount;
	@Transient
	int discountPrice;
	@Transient
	int productQuantity;
	public int getProductQuantity() {
		return productQuantity;
	}
	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}
	public int getDiscountPrice() {
		return discountPrice;
	}
	public void setDiscountPrice(int discountPrice) {
		this.discountPrice = discountPrice;
	}
	@Lob
	@Column(columnDefinition = "MEDIUMBLOB")
	byte[] image;
	
	
	String imagePath;
	
	@ManyToMany(mappedBy = "listOfProducts")
    private List<OrderProducts> orders;
	
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	
	public List<OrderProducts> getListOfProducts() {
		return orders;
	}

	public void setListOfOrderProducts(List<OrderProducts> listOfOrderProducts) {
		this.orders = listOfOrderProducts;
	}
	
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", productName=" + productName + ", price=" + price + ", category="
				+ category + ", discount=" + discount + ", discountPrice=" + discountPrice + ", productQuantity="
				+ productQuantity + "]";
	}

	
}

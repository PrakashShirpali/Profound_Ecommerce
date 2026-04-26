/*
 * package com.example.ecommerce.entity;
 * 
 * import java.time.LocalDate; import java.util.List;
 * 
 * import jakarta.persistence.Entity; import jakarta.persistence.GeneratedValue;
 * import jakarta.persistence.Id; import jakarta.persistence.JoinColumn; import
 * jakarta.persistence.OneToMany; import jakarta.persistence.OneToOne;
 * 
 * @Entity public class OrderProducts {
 * 
 * @Id
 * 
 * @GeneratedValue int orderId; int totalPrice; LocalDate orderDate; int userId;
 * String statusPending; String statusType;
 * 
 * @OneToMany
 * 
 * @JoinColumn(name="orderId") List<Product> listOfProducts;
 * 
 * @OneToOne
 * 
 * @JoinColumn(name="orderId") Address address;
 * 
 * public int getOrderId() { return orderId; } public void setOrderId(int
 * orderId) { this.orderId = orderId; } public int getTotalPrice() { return
 * totalPrice; } public void setTotalPrice(int totalPrice) { this.totalPrice =
 * totalPrice; } public LocalDate getOrderDate() { return orderDate; } public
 * void setOrderDate(LocalDate orderDate) { this.orderDate = orderDate; } public
 * int getUserId() { return userId; } public void setUserId(int userId) {
 * this.userId = userId; } public String getStatusPending() { return
 * statusPending; } public void setStatusPending(String statusPending) {
 * this.statusPending = statusPending; }
 * 
 * public List<Product> getListOfProducts() { return listOfProducts; } public
 * void setListOfProducts(List<Product> listOfProducts) { this.listOfProducts =
 * listOfProducts; } public Address getAddress() { return address; } public void
 * setAddress(Address address) { this.address = address; }
 * 
 * 
 * public String getStatusType() { return statusType; } public void
 * setStatusType(String statusType) { this.statusType = statusType; }
 * 
 * @Override public String toString() { return "OrderProducts [orderId=" +
 * orderId + ", totalPrice=" + totalPrice + ", orderDate=" + orderDate +
 * ", userId=" + userId + ", statusPending=" + statusPending + ", statusType=" +
 * statusType + ", listOfProducts=" + listOfProducts + ", address=" + address +
 * "]"; }
 * 
 * 
 * 
 * }
 */

package com.example.ecommerce.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;

@Entity
public class OrderProducts {

    @Id
    @GeneratedValue
    private int orderId;

    private int totalPrice;
    private LocalDate orderDate;
    private int userId;
    private String statusPending;
    private String statusType;

    @ManyToMany
    private List<Product> listOfProducts;

    @OneToOne
    private Address address;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getStatusPending() {
		return statusPending;
	}

	public void setStatusPending(String statusPending) {
		this.statusPending = statusPending;
	}

	public String getStatusType() {
		return statusType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public List<Product> getListOfProducts() {
		return listOfProducts;
	}

	public void setListOfProducts(List<Product> products) {
		this.listOfProducts = products;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "OrderProducts [orderId=" + orderId + ", totalPrice=" + totalPrice + ", orderDate=" + orderDate
				+ ", userId=" + userId + ", statusPending=" + statusPending + ", statusType=" + statusType
				+ ", listOfProducts=" + listOfProducts + ", address=" + address + "]";
	}

	
}

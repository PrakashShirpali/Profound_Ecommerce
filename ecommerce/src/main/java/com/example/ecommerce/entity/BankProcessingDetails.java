package com.example.ecommerce.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.ToString;

@Entity
@Data
@ToString
public class BankProcessingDetails {
	@Id
	@GeneratedValue
	private int id;
	
	@Column(unique = true)
	private int userId;
    private String pincode;
    private String city;
    private String addressId;
    private String paymentMode;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "bank_details_id") // Use a different name for the join column
    private BankDetails bankDetails;

}

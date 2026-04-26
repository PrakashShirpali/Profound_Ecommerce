package com.example.ecommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Category {
	@Id
	@GeneratedValue
	int categoryId;
	String fashion;
	String gadgets;
	String footwear;
	public String getFashion() {
		return fashion;
	}
	public void setFashion(String fashion) {
		this.fashion = fashion;
	}
	public String getGadgets() {
		return gadgets;
	}
	public void setGadgets(String gadgets) {
		this.gadgets = gadgets;
	}
	public String getFootwear() {
		return footwear;
	}
	public void setFootwear(String footwear) {
		this.footwear = footwear;
	}
	@Override
	public String toString() {
		return "Category [fashion=" + fashion + ", gadgets=" + gadgets + ", footwear=" + footwear + "]";
	}
}

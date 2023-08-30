package com.cook.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CookPick_table")
public class CookPick {
	@Id
	private Integer productId;
	private String productName;
	private String productVendor;
	private Double  productPrice;
	private Integer productInStock;
	
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
	public String getProductVendor() {
		return productVendor;
	}
	public void setProductVendor(String productVendor) {
		this.productVendor = productVendor;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public int getProductInStock() {
		return productInStock;
	}
	public void setProductInStock(int productInStock) {
		this.productInStock = productInStock;
	}
	
	

}

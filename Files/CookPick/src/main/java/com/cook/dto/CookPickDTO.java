package com.cook.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.NonNull;

public class CookPickDTO {
	@NonNull
	private Integer productId;
	
	@Length(max = 10,message ="{greater.than}")
	@Length(min = 5,message="{less.than}")
	private String productName;
	@NotBlank
	private String productVendor;
	@Positive
	@Digits(integer = 5,fraction = 2, message ="{price}")
	private Double  productPrice;
	@Positive
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

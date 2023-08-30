package com.cook.util;

public enum CookPickConstansts {
	
	PRODUCT_NOT_FOUND("product.not.found"),
	SERVER_ERROR("server.error.occured");
	
	String type;

	private CookPickConstansts(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return this.type;
	}
	
	
}

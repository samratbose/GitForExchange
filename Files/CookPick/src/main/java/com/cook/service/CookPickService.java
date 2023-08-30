package com.cook.service;



import java.util.List;

import com.cook.dto.CookPickDTO;
import com.cook.entity.CookPick;
import com.cook.exception.ProductNotFoundException;


public interface CookPickService {
	public String addProduct(CookPickDTO cookPick);
	public Integer addingProduct(Integer cookPickDTO);
	public String deleteProduct(Integer cookPickDTO) throws ProductNotFoundException;
	public CookPickDTO updateProduct(Integer cookPickDTO,CookPickDTO cookPickDTO2);
	public List<CookPick> getProducts (String productName, String productVendor);
	

}

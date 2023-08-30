package com.cook.service;

import java.util.List;
import java.util.Optional;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.cook.dto.CookPickDTO;
import com.cook.entity.CookPick;
import com.cook.exception.ProductNotFoundException;
import com.cook.repository.CookPickRepository;
import com.cook.util.CookPickConstansts;

@Service
@PropertySource("classpath:ValidationMessages.properties")
public class CookPickServiceImpl implements CookPickService {
	@Autowired
	CookPickRepository cookPickRepository;
	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	Environment environment;

	@Override
	public String addProduct(CookPickDTO cookPick) {
		// TODO Auto-generated method stub
		cookPickRepository.saveAndFlush(mapper.map(cookPick,CookPick.class));
		return mapper.map(cookPick,CookPick.class).getProductName()+" is added successfully";
	}

	@Override
	public Integer addingProduct(Integer cookPickDTO) {
		CookPick cookPick= cookPickRepository.getById(cookPickDTO);
		return mapper.map(cookPick,CookPickDTO.class).getProductId();
	}

	@Override
	public String deleteProduct(Integer cookPickDTO) throws ProductNotFoundException{
		//cookPickRepository.findById(cookPickDTO);
		if(cookPickRepository.findById(cookPickDTO).isPresent()) {
			cookPickRepository.deleteById(cookPickDTO);
			return "ProductId "+cookPickDTO+" Deleted Successfully";
			
		}
		else {
			  throw new ProductNotFoundException(environment.getProperty(CookPickConstansts.PRODUCT_NOT_FOUND.toString()));  
		}
		
		
	}

	@Override
	public CookPickDTO updateProduct(Integer cookPickDTO, CookPickDTO cookPickDTO2) {
		Optional<CookPick> optional=cookPickRepository.findById(cookPickDTO);
		CookPick cookPick=null;
		if(optional.isPresent()) {
			cookPick=optional.get();
			cookPick.setProductPrice(cookPickDTO2.getProductPrice());
			
			cookPickRepository.saveAndFlush(cookPick);
		}
		return mapper.map(cookPick, CookPickDTO.class);
	}

	@Override
	public List<CookPick> getProducts(String productName, String productVendor) {
		// TODO Auto-generated method stub
		return cookPickRepository.fetchCallDetails(productName, productVendor);
	}

	

	
	

	

	
	

	
	
	

}

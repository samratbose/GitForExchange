package com.cook.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cook.dto.CookPickDTO;
import com.cook.entity.CookPick;
import com.cook.exception.ProductNotFoundException;
import com.cook.service.CookPickService;




@RestController
@RequestMapping("/product")
public class CookPickController {
	
	@Autowired
	CookPickService cookPickService;
	
	@Autowired
	ModelMapper mapper;
	
	@PostMapping
	public String addProduct(@Valid@RequestBody CookPickDTO product) {
		return cookPickService.addProduct(product);
		
	}
	
	@GetMapping(value="/{productId}")
	public ResponseEntity<Integer> createProduct(@PathVariable Integer productId){
		return ResponseEntity.status(HttpStatus.FOUND).body(cookPickService.addingProduct(productId));
		
	}
	/*
	 * @GetMapping(value="/{productId}",params = "version=1") public
	 * ResponseEntity<CookPickDTO>createProductVersion1(@PathVariable Integer
	 * productId){ return
	 * ResponseEntity.status(HttpStatus.FOUND).body(cookPickService.addingProduct(
	 * productId));
	 * 
	 * }
	 */
	
	@DeleteMapping(value="/{productId}")
	public ResponseEntity<String> deleteFood(@PathVariable Integer productId) throws ProductNotFoundException {
		return ResponseEntity.status(HttpStatus.OK).body(cookPickService.deleteProduct(productId));
	}
	
	@PutMapping(value="/{productId}")
	ResponseEntity<CookPickDTO> update(@PathVariable("productId") Integer productPrice, @RequestBody CookPickDTO cookPickDTO){
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(cookPickService.updateProduct(productPrice, cookPickDTO));
		
	}
	
	@GetMapping(produces = "application/json")
	ResponseEntity<List<CookPickDTO>> getEntity(@PathParam(value = "productName") String productName,@PathParam(value = "productVendor") String productVendor){
		List<CookPickDTO> list=new ArrayList<>();
		List<CookPick> picks=cookPickService.getProducts(productName, productVendor);
		for(CookPick cookPick:picks) {
			list.add(mapper.map(cookPick, CookPickDTO.class));
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(list);
		
	}

}

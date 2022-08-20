package com.masai.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Product;
import com.masai.service.ProductService;

@RestController
public class ProductController {
	
	
	@Autowired
	private ProductService prodService;
	

	@GetMapping("/product/{productName}")
	public ResponseEntity<Product> getProductByNameHandler(@PathVariable("productName") String productName){
		
		Product product = prodService.getProductDetailsByName(productName);
		
		return new ResponseEntity<Product>(product,HttpStatus.OK);
	}
	
	
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProductDetailsHandler(){
		
		List<Product> productList = prodService.getAllProductDetails();
		
		return new ResponseEntity<List<Product>>(productList,HttpStatus.OK);
	}
}

package com.masai.service;

import java.util.List;

import com.masai.model.Product;

public interface ProductService {

	public Product addProduct(Product product,String key);

	public Product updateProduct(Product product,String key);

//	public List<Product> getProductList(String key);
//
//	public Product getProductByName(String name,String key);

	public Product deleteProductByName(String name,String key);
	
	public Product getProductDetailsByName(String productName);
		
	public List<Product> getAllProductDetails();

	



}

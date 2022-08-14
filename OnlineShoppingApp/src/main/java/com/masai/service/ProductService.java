package com.masai.service;

import com.masai.model.Product;
import java.util.*;

public interface ProductService {

	public Product addProduct(Product product);
	
	public Product updateProduct(Product product);
	
	public List<Product> getProductList();
	
	public Product getProductByName(String name);
	
	public Product deleteProductByName(String name);
	
}

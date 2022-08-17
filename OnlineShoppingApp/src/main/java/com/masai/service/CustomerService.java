package com.masai.service;

import java.util.List;

import com.masai.model.Customer;
import com.masai.model.Product;


public interface CustomerService {
  
	
	public Product getProductDetailsByName(String productName);
	
	public List<Product> getAllProductDetails();

	public String addProductToCart(String productName, Integer quantity, String key);
	
	public String removeProductFromCart(String productName, String key);
	
	public String orderProductFromCart(String key);

	
	public Customer createCustomer(Customer customer);
	
    public Customer updateCustomer(Customer customer, String key);
	
	public Customer deleteCustomer(String key);
	
	public Customer getCustomerDetails(String key);

	
	
	
	
	
}

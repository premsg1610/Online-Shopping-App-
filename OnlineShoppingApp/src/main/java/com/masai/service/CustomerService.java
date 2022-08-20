package com.masai.service;

import com.masai.model.Customer;


public interface CustomerService {
 

	public String addProductToCart(String productName, Integer quantity, String key);
	
	public String removeProductFromCart(String productName, String key);
	
	public String orderProductFromCart(String key);
	
	public Customer registerCustomer(Customer customer);
	
    public Customer updateCustomer(Customer customer, String key);
	
	public Customer deleteCustomer(String key);
	
	public Customer getCustomerDetails(String key);

	
	
}

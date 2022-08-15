package com.masai.service;

import java.util.List;

import com.masai.model.Customer;
import com.masai.model.Product;


public interface CustomerService {
  
	public Customer registerCustomer(Customer customer);
	 
	public Customer deleteCustomerById(Integer customerId);
	
	public Customer updateCustomerById(Customer customer);
	
	public  List<Customer> getAllCustomerDetails();
	
	public Customer getCustomerDetails(Integer customerId);

	public String addProductToCart(String productName, Integer quantity, String mobile);
	
	public String removeProductFromCart(String productName, String mobile);
	
	public Product updateProductQuantity(String productName);

	
	
	
	
	//By -> Harshit//
//    public Customer updateCustomer(Customer customer, String key);
//	
//	public Customer deleteCustomer(String key);
//	
//	public Customer getCustomerDetails(String key);
	
	
	
	
}

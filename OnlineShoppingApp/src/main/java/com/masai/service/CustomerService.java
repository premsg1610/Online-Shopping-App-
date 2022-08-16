package com.masai.service;

import java.util.List;

import com.masai.model.Customer;
import com.masai.model.Product;


public interface CustomerService {
  
//	public Customer registerCustomer(Customer customer);
	 
//	public Customer deleteCustomerById(Integer customerId);
//	
//	public Customer updateCustomerById(Customer customer);
	
//	public  List<Customer> getAllCustomerDetails();
	
//	public Customer getCustomerDetails(Integer customerId);

	public String addProductToCart(String productName, Integer quantity, String key);
	
	public String removeProductFromCart(String productName, String key);
	
	public Product updateProductQuantity(String productName);

	
	

//	public Product addProductToCart(Product product,String mobile);
	



	
	//By -> Harshit//
	
	public Customer createCustomer(Customer customer);
	
    public Customer updateCustomer(Customer customer, String key);
	
	public Customer deleteCustomer(String key);
	
	public Customer getCustomerDetails(String key);
	
	
	
	
}

package com.masai.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.masai.model.Customer;


public interface CustomerService {
  
	public Customer registerCustomer(Customer customer);
	 
	public Customer deleteCustomerById(Integer customerId);
	
	public Customer updateCustomerById(Customer customer);
	
	public  List<Customer> getAllCustomerDetails();
	
	public Customer getCustomerDetails(Integer customerId);
	
	
}

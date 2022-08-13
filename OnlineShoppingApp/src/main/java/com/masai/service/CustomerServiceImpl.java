package com.masai.service;

import java.util.List;
import java.util.Optional;

import com.masai.exceptions.CustomerException;
import com.masai.model.Customer;
import com.masai.repository.CustomerDao;



public class CustomerServiceImpl implements CustomerService{
         
	private CustomerDao cusDao;
	
	
	//The method to get all customer details
	@Override
	public List<Customer> getAllCustomerDetails() {
		  
		  List<Customer> cusList= cusDao.findAll();
		  
		  if(!cusList.isEmpty()) {
			  return cusList;
		  }
		  
		  throw new CustomerException("No customer found"); 
	}
	
	
	//The method to get a customer details
	@Override
	public Customer getCustomerDetails(Integer customerId) {
		
		Optional<Customer> customerOpt = cusDao.findById(customerId);
		
		  if(customerOpt.isPresent()) {
			 
			 return customerOpt.get();
		  }
	
			throw new CustomerException("Customer does not exist with customer id :"+customerId);
	}

	

	//The method to register the customer
	@Override
	public Customer registerCustomer(Customer customer) {
		        
	Optional<Customer> customerOpt = cusDao.findById(customer.getCustomerId());
	  if(!customerOpt.isPresent()) {
		return cusDao.save(customer);
	  }
	
		throw new CustomerException("Customer already exist.");
	}

	
	//The method to delete the customer by Id
	@Override
	public Customer deleteCustomerById(Integer customerId) {
		
		Optional<Customer> customerOpt = cusDao.findById(customerId);
		
		  if(customerOpt.isPresent()) {
			 cusDao.deleteById(customerId);
			 return customerOpt.get();
		  }
	
			throw new CustomerException("Customer does not exist with customer id :"+customerId);
	}


	//The method to update the customer byId
	@Override
	public Customer updateCustomerById(Integer customerId, Customer customer) {
		
	    	Optional<Customer> customerOpt = cusDao.findById(customerId);
		     
	    	if(customerOpt.isPresent()) {
			      
	    	return cusDao.save(customer);
		   }
	  
	    	throw new CustomerException("Customer does not exist with customer id :"+customerId);
	}


		
	
}

package com.masai.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.CustomerException;
import com.masai.model.Cart;
import com.masai.model.Customer;
import com.masai.model.Product;
import com.masai.repository.CartDao;
import com.masai.repository.CustomerDao;


@Service
public class CustomerServiceImpl implements CustomerService{
         
	@Autowired
	private CustomerDao cusDao;
	
	@Autowired
	private CartDao cartDao;
	
	
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
		
	Customer customerOpt = cusDao.findByMobile(customer.getMobile());

//	 System.out.println(customerOpt.getMobile());
	  if(customerOpt == null) {
		Customer newCustomer = cusDao.save(customer);
		return newCustomer;
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
	public Customer updateCustomerById(Customer customer) {
		
	    	Optional<Customer> customerOpt = cusDao.findById(customer.getCustomerId());
		     
	    	if(customerOpt.isPresent()) {
			      
	    	return cusDao.save(customer);
		   }
	  
	    	throw new CustomerException("Customer does not exist with customer id :"+ customer.getCustomerId());
	}


	@Override
	public Product addProductToCart(Product product,String mobile) {
	
	     Customer customerOpt = cusDao.findByMobile(mobile);
	     
		   if(customerOpt!=null) {
			
		      if(customerOpt.getCart()!=null) {
		    	  
		    	  List<Product> productList =	customerOpt.getCart().getProductList();
				  Boolean flag=false;
				  
				  for(Product p:productList) {
					
					if(p.getProductName().equals(product.getProductName())){
		            	p.setQuantity(p.getQuantity()+product.getQuantity());
		            	flag=true;
		            	
		            	
		            	
					}
		         
				}	
				if(flag=false) {
					productList.add(product);
				}
			
		    }
		      else {
					Cart newCart=new Cart();
					newCart.setProductList(new ArrayList<>());
					customerOpt.setCart(newCart);
					customerOpt.getCart().getProductList().add(product);
					
				}
		      
		
		
		   cusDao.save(customerOpt);
		  return product;

		  }
	  
	    	throw new CustomerException("Customer does not exist with customer id :"+ customerOpt.getCustomerId());
		 
	    	
	}
	
	
	
	



		
	
}

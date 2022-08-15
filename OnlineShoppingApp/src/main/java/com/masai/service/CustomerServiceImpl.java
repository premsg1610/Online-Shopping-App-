package com.masai.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.CustomerLogin.GetCurrentLoginUserSessionDetailsImpl;
import com.masai.exceptions.CustomerException;
import com.masai.exceptions.LoginException;
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


	
	//Harshit//
	
	
	@Autowired
	private GetCurrentLoginUserSessionDetailsImpl getCurrentLoginUser;
	
	
	@Override
	public Customer createCustomer(Customer customer) {
		
	 Customer opt =	 cusDao.findByMobile(customer.getMobile());
	 
	 if(opt == null) {
		 return cusDao.save(customer);
	 }else {
		 throw new LoginException("User is already register");
	 }
	 
	 
	}


	@Override
	public Customer updateCustomer(Customer customer, String key) {
       Customer customer2 = getCurrentLoginUser.getCurrentCustomer(key);
		
		if(customer2 == null) {
			throw new LoginException("No user found.. try login first");
		}
		
		if(!customer2.getEmail().equals(customer.getEmail())) {
			customer2.setEmail(customer.getEmail());
		}
		else if(!customer2.getMobile().equals(customer.getMobile())) {
			customer2.setMobile(customer.getMobile());
		}
		else if(!customer2.getFirstName().equals(customer.getFirstName())) {
			customer2.setFirstName(customer.getFirstName());
		}
		else if(!customer2.getPassword().equals(customer.getPassword())) {
			customer2.setPassword(customer.getPassword());
		}
		
		
		return cusDao.save(customer2);
	}


	@Override
	public Customer deleteCustomer(String key) {
		Customer customer = getCurrentLoginUser.getCurrentCustomer(key);		
		cusDao.delete(customer);
		return customer;
	}


	@Override
	public Customer getCustomerDetails(String key) {
		Customer customer = getCurrentLoginUser.getCurrentCustomer(key);	
		System.out.println(customer);
		return customer;
	}

	
		
	
}

package com.masai.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.CustomerLogin.GetCurrentLoginUserSessionDetailsImpl;
import com.masai.exceptions.CustomerException;

import com.masai.exceptions.LoginException;

import com.masai.exceptions.ProductException;
import com.masai.model.Cart;
import com.masai.model.CartItem;
import com.masai.model.Customer;
import com.masai.model.Product;
import com.masai.repository.CartDao;
import com.masai.repository.CartItemDao;
import com.masai.repository.CustomerDao;
import com.masai.repository.ProductDao;


@Service
public class CustomerServiceImpl implements CustomerService{
         
	@Autowired
	private CustomerDao cusDao;
	
	@Autowired
	private CartDao cartDao;
	
	@Autowired
	private ProductDao prodDao;
	
	@Autowired
	private CartItemDao cartItemDao;
	
	
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
	public Product updateProductQuantity(String productName) {
		
		Product existingProduct = prodDao.findByProductName(productName);
		existingProduct.setQuantity(existingProduct.getQuantity()-1);
		return existingProduct;
	
	}
	
	
	
	
	@Override
	public String addProductToCart(String productName, Integer quantity, String mobile) {
		
		Customer customer = cusDao.findByMobile(mobile);
		
		if(customer != null)
		{
			Product existingProduct = prodDao.findByProductName(productName);
			
			if(existingProduct != null) 
			{
				if(existingProduct.getQuantity() >= quantity)
				{
				
					if(customer.getCart() == null)
					{
						Cart cart = new Cart();
						
						cart.setCartItemList(new ArrayList<>());
						
						CartItem newCartItem = new CartItem();
						
						newCartItem.setProductQuantity(quantity);
						newCartItem.setProduct(existingProduct);
						
						cart.getCartItemList().add(newCartItem);
						
						customer.setCart(cart);
						System.out.println("end of new cart method");
						prodDao.save(existingProduct);
						cusDao.save(customer);
						
						cartItemDao.save(newCartItem);
					}
					
					else 
					{
						List<CartItem> cartItemList = customer.getCart().getCartItemList();
						
						boolean flag = false;
						
						for(CartItem item : cartItemList)
						{
							if(item.getProduct().getProductName().equals(productName))
							{
								item.setProductQuantity(item.getProductQuantity() + quantity);
								flag = true;
								cartItemDao.save(item);
								break;
							}
						}
						
						if(flag == false)
						{
							CartItem cartItem = new CartItem();
							
							cartItem.setProductQuantity(quantity);
							cartItem.setProduct(existingProduct);
							
							cartItemList.add(cartItem);
							cartItemDao.save(cartItem);
						}
					}
					
					existingProduct.setQuantity(existingProduct.getQuantity() - quantity);
					
					prodDao.save(existingProduct);
					cusDao.save(customer);
					
					return "Product " + productName + " added to cart."; 
				}
				
				throw new ProductException("Your quantity: " + quantity + " is more than available quantity: " + existingProduct.getQuantity());
			}
			
			throw new ProductException("Product out of stock.");
		}

		throw new CustomerException("Customer not logged in");
	}


	
	
	
//	delete item from cart and it should return to database
	
	
	@Override
	public String removeProductFromCart(String productName, String mobile) {
		
			Customer customer = cusDao.findByMobile(mobile);
					
			if(customer != null)
			{
				List<CartItem> cartItemList = customer.getCart().getCartItemList();
				
				for(CartItem c: cartItemList)
				{
					if(c.getProduct().getProductName().equals(productName))
					{
						c.setProductQuantity(c.getProductQuantity() - 1);
						System.out.println(c.getProductQuantity() + " after removing from cart");
//						cartItemDao.save(c);
						if(c.getProductQuantity() == 0)
						{
							cartItemList.remove(c);
							System.out.println("yha tak chal gya");
//							cartDao.deleteByName(c.getProduct().getProductName());
							cartItemDao.deleteById(c.getCartItemId());
							
						}
						
						System.out.println("yha tak chal gya 2");
						Product product = prodDao.findByProductName(productName);
						
						product.setQuantity(product.getQuantity() + 1);
						System.out.println("yha tak chal gya 3");
						prodDao.save(product);
						cartItemDao.save(c);
//						cartDao.save(customer.getCart());
						
						if(cartItemList.size() == 0)
						{
							return "Product " +  productName + " removed from cart successfully." + " Cart is empty now.";
						}
						
						return "Product " +  productName + " removed from cart successfully.";
					}
				}
			}
			
			throw new CustomerException("Customer not logged in.");
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

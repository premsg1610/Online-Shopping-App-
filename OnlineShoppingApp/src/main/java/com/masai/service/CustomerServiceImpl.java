package com.masai.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.LoginException;
import com.masai.exceptions.ProductException;
import com.masai.model.Cart;
import com.masai.model.CartItem;
import com.masai.model.Customer;
import com.masai.model.OrderItems;
import com.masai.model.Orders;
import com.masai.model.Product;
import com.masai.repository.CartItemDao;
import com.masai.repository.CustomerDao;
import com.masai.repository.ProductDao;
import com.masai.util.GetCurrentLoginUserSessionDetailsImpl;


@Service
public class CustomerServiceImpl implements CustomerService{
         
	@Autowired
	private CustomerDao cusDao;
	
//	@Autowired
//	private CartDao cartDao;
//	
	@Autowired
	private ProductDao prodDao;
	
	@Autowired
	private CartItemDao cartItemDao;
	
	@Autowired
	private GetCurrentLoginUserSessionDetailsImpl getCurrentLoginUser;


	
	@Override
	public String addProductToCart(String productName, Integer quantity, String key) {
		
		Customer customer = getCurrentLoginUser.getCurrentCustomer(key);
		
//		Customer customer = cusDao.findByMobile(mobile);
		
		if(customer != null)
		{
			Product existingProduct = prodDao.findByProductName(productName);
			
			if(existingProduct != null) 
			{
				
				if(existingProduct.getQuantity()>0) 
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
			
			throw new ProductException("Product not found.");
			
		}

		throw new LoginException("Customer not logged in");
	}


	
	
	
//	delete item from cart and it should return to database
	
	
	@Override
	public String removeProductFromCart(String productName, String key) {
		
		Customer customer = getCurrentLoginUser.getCurrentCustomer(key);
		
//			Customer customer = cusDao.findByMobile(mobile);
					
			if(customer != null)
			{
				List<CartItem> cartItemList = customer.getCart().getCartItemList();
				
				for(CartItem c: cartItemList)
				{
					if(c.getProduct().getProductName().equals(productName))
					{
						c.setProductQuantity(c.getProductQuantity() - 1);
						
//						cartItemDao.save(c);
						if(c.getProductQuantity() == 0)
						{
							int id = c.getCartItemId();
							cartItemList.remove(c);
			
//							cartDao.deleteByName(c.getProduct().getProductName());
							cartItemDao.deleteById(id);
							
						}
						
						Product product = prodDao.findByProductName(productName);
						
						product.setQuantity(product.getQuantity() + 1);
				
						prodDao.save(product);
						
						if(c.getProductQuantity() != 0)
						{
							cartItemDao.save(c);
						}
						
						
//						cartDao.save(customer.getCart());
						
						if(cartItemList.size() == 0)
						{
							return "Product " +  productName + " removed from cart successfully." + " Cart is empty now.";
						}
						
						return "Product " +  productName + " removed from cart successfully.";
					}
				}
				throw new LoginException("Cart doesn't have product " + productName + ". Add item to cart...");
			}
			
			throw new LoginException("Customer not logged in.");
	}

	
	
	
	

	@Override
	public String orderProductFromCart(String key) {

		Customer customer = getCurrentLoginUser.getCurrentCustomer(key);
		
		if(customer != null)
		{
			List<CartItem> cartItemList = customer.getCart().getCartItemList();
			
			if(cartItemList.size() > 0)
			{
				
	//			System.out.println(cartItemList.size());
				
				Orders order = new Orders();
				
				order.setOrderDateTime(LocalDateTime.now());
				order.setOrderStatus("Order Placed Successfully.");
				order.setOrderItemsList(new ArrayList());
				
				
				List<CartItem> newList = new ArrayList<>();
				
				for(CartItem c: cartItemList)
				{
					OrderItems orderItem = new OrderItems();
					orderItem.setProduct(c.getProduct());
					orderItem.setProductQuantity(c.getProductQuantity());
					order.getOrderItemsList().add(orderItem);
					
					newList.add(c);
					
				}
				
			
			
				for(CartItem c: newList)
				{
					int id = c.getCartItemId();
					cartItemList.remove(c);
					
					cartItemDao.deleteById(id);
					
					System.out.println("one product ordered");
				}
				
			
				
				if(customer.getOrderList() == null)
				{
					customer.setOrderList(new ArrayList<>());
					
					customer.getOrderList().add(order);
				}
				else {
					customer.getOrderList().add(order);
				}
				
				cusDao.save(customer);
				
				return order.getOrderStatus();
	
			}
			throw new ProductException("No product in cart . Add product to cart first...");
		}
		
		throw new LoginException("Customer not logged in");

	}





	
	
	@Override
	public Customer registerCustomer(Customer customer) {
		
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
		
		return cusDao.save(customer);
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

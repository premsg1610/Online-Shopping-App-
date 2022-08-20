package com.masai.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Customer;
import com.masai.service.CustomerService;
import com.masai.service.ProductService;



@RestController
@RequestMapping("/customer")
public class CustomerController {
    
	@Autowired
	private CustomerService cusService;
	
	
	@Autowired
	private ProductService prodService;
	


	
	
	@PostMapping("/cart/{productName}/{quantity}/")
	public ResponseEntity<String> addProductToCartHandler(@PathVariable("productName") String productName,
															@PathVariable("quantity") Integer quantity,
												            @RequestParam(required = false) String key){
		
		
		 String message =  cusService.addProductToCart(productName, quantity, key);
		
		   return new ResponseEntity<String>(message,HttpStatus.ACCEPTED);
		
	}
	
	
	
	
	@PostMapping("/order/")
	public ResponseEntity<String> orderProductsHandler(@RequestParam(required = false) String key){
		
		String message = cusService.orderProductFromCart(key);
		return new ResponseEntity<String>(message,HttpStatus.ACCEPTED);
	}
	
	
	
	
	
	
	@PostMapping("/cart/{productName}/")
	public ResponseEntity<String> removeProductFromCartHandler(@PathVariable("productName") String productName,
			                                                   @RequestParam(required = false) String key){
								
		String message =  cusService.removeProductFromCart(productName, key);
		
		 return new ResponseEntity<String>(message,HttpStatus.ACCEPTED);
		
		
	}
	
	
	
	
	@PostMapping("/")
	public Customer registerCustomerHandler(@Valid @RequestBody Customer customer) {
		
		
	return 	cusService.registerCustomer(customer);
		
	}	
	
	
	
	// To update existing user details by passing its login key
		@PutMapping("/")
		public Customer updateCustomerHandler(@Valid @RequestBody Customer customer, @RequestParam(required = false) String key) {
			return cusService.updateCustomer(customer, key);
		}
		
		
		
	
	
		// To delete existing user details by passing its login key
		@DeleteMapping(value = "/")
		public Customer deleteCustomerHandler(@RequestParam(required = false) String key) {
			return cusService.deleteCustomer(key);
		}
		
		
		// To get details of current user by passing its login key
		@GetMapping(value = "/")
		public Customer getCustomerDetailsHandler(@RequestParam(required = false) String key) {
			return cusService.getCustomerDetails(key);
		}
	
	
}

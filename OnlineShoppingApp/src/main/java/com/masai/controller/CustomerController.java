package com.masai.controller;

import java.util.List;

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
import com.masai.model.Product;
import com.masai.repository.ProductDao;
import com.masai.service.CustomerService;
import com.masai.service.ProductService;



@RestController
@RequestMapping("/customer")
public class CustomerController {
    
	@Autowired
	private CustomerService cusService;
	
	
	@Autowired
	private ProductService prodService;
	

	
	
	@GetMapping("/product/{productName}")
	public ResponseEntity<Product> getProductByNameHandler(@PathVariable("productName") String productName){
		
		Product product = cusService.getProductDetailsByName(productName);
		
		return new ResponseEntity<Product>(product,HttpStatus.OK);
	}
	
	
	@GetMapping("/product")
	public ResponseEntity<List<Product>> getAllProductDetailsHandler(){
		
		List<Product> productList = cusService.getAllProductDetails();
		
		return new ResponseEntity<List<Product>>(productList,HttpStatus.OK);
	}
	

	
	
	@PostMapping("/cart/{productName}/{quantity}/{key}")
	public ResponseEntity<String> addProductToCartHandler(@PathVariable("productName") String productName,
															@PathVariable("quantity") Integer quantity,
												            @PathVariable("key") String key){
		
		
		 String message =  cusService.addProductToCart(productName, quantity, key);
		
		   return new ResponseEntity<String>(message,HttpStatus.ACCEPTED);
		
	}
	
	
	
	
	@PostMapping("/order/{key}")
	public ResponseEntity<String> orderProductsHandler(@PathVariable("key") String key){
		
		String message = cusService.orderProductFromCart(key);
		return new ResponseEntity<String>(message,HttpStatus.ACCEPTED);
	}
	
	
	
	
	
	
	@PostMapping("/removeCart/{productName}/{key}")
	public ResponseEntity<String> removeProductFromCart(@PathVariable("productName") String productName,
			                                            @PathVariable("key") String key){
								
		String message =  cusService.removeProductFromCart(productName, key);
		
		 return new ResponseEntity<String>(message,HttpStatus.ACCEPTED);
		
		
	}
	
	
	
	
//	           =================== Harshit ======================
	
	
	
//	@GetMapping("/Phelp")
//	public String getTest() {
//		return "Jay Shri Krishna";
//	}
//	
	
	@PostMapping("/resigsterCustomerByDetails")
	public Customer registerCustomer(@Valid @RequestBody Customer customer) {
		
		
	return 	cusService.createCustomer(customer);
		
	}	
	
	
	
	// To update existing user details by passing its login key
		@PutMapping(value = "/updateCustomerByKey")
		public Customer updateCustomer( @RequestBody Customer customer, @RequestParam(required = false) String key) {
			return cusService.updateCustomer(customer, key);
		}
		
		
		
	
	
		// To delete existing user details by passing its login key
		@DeleteMapping(value = "/deleteCustomerByKey")
		public Customer deleteCustomer(@RequestParam(required = false) String key) {
			return cusService.deleteCustomer(key);
		}
		
		
		// To get details of current user by passing its login key
		@GetMapping(value = "/getCustomerByKey")
		public Customer getCustomerDetails(@RequestParam(required = false) String key) {
			return cusService.getCustomerDetails(key);
		}
	
	
}

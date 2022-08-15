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
import com.masai.service.CustomerService;
import com.masai.service.ProductService;



@RestController
@RequestMapping("/customer")
public class CustomerController {
    
	@Autowired
	private CustomerService cusService;
	
	
	@Autowired
	private ProductService prodService;
	
	
	
	@GetMapping("/")
	public ResponseEntity<List<Customer>> getAllCustomerDetailsHandler(){
		
		List<Customer> customerList = cusService.getAllCustomerDetails();
		
		return new ResponseEntity<>(customerList,HttpStatus.OK);
	}

     
	@GetMapping("/{customerId}/")
	public ResponseEntity<Customer> getCustomerDetailsById(@PathVariable("customerId") Integer customerId){
		   Customer existingCustomer = cusService.getCustomerDetails(customerId);
		   return new ResponseEntity<>(existingCustomer,HttpStatus.OK);
	}


	
	@PostMapping("/")
     public ResponseEntity<Customer> registerCustomerHandler(@Valid @RequestBody Customer customer){
   
	   Customer newCustomer = cusService.registerCustomer(customer);

	  return new ResponseEntity<Customer>(newCustomer,HttpStatus.CREATED);
		
     }
	
	
	
	
	@DeleteMapping("/{customerId}/")
    public ResponseEntity<Customer> deleteCustomerHandler(@PathVariable ("customerId") Integer customerId){
   	 
	   Customer deletedCustomer = cusService.deleteCustomerById(customerId);
	   
	  return new ResponseEntity<>(deletedCustomer,HttpStatus.OK);
		
    }
	
	
	
	
	@PutMapping("/")
    public ResponseEntity<Customer> updateCustomerHandler(@Valid @RequestBody Customer customer){
   	 
	   Customer updatedCustomer = cusService.updateCustomerById(customer);
	   
	  return new ResponseEntity<>(updatedCustomer,HttpStatus.ACCEPTED);
		
    }
	
	
	
//	@PostMapping("/product")
//    public ResponseEntity<Product> addProductHandler(@Valid @RequestBody Product product){
//  
//	   Product newProduct = prodService.addProduct(product);
//
//	  return new ResponseEntity<Product>(newProduct,HttpStatus.CREATED);
//		
//    }
	
//	@PostMapping("/cart/{mobile}")
//	public ResponseEntity<Product> addProductToCartHandler(@Valid @RequestBody Product product ,@PathVariable("mobile") String mobile){
//		
//		 Product product2=  cusService.addProductToCart(product, mobile);
//		
//		   return new ResponseEntity<Product>(product2,HttpStatus.CREATED);
//		
//	}
	
	
	@PostMapping("/cart/{productName}/{quantity}/{mobile}")
	public ResponseEntity<String> addProductToCartHandler(@PathVariable("productName") String productName,
															@PathVariable("quantity") Integer quantity,
												            @PathVariable("mobile") String mobile){
		
		
		 String message =  cusService.addProductToCart(productName, quantity, mobile);
		
		   return new ResponseEntity<String>(message,HttpStatus.ACCEPTED);
		
	}
	
	
	@PutMapping("/product/{productName}")
    public ResponseEntity<Product> updateProductHandler(@PathVariable("productName") String productName){
  
	   Product updatedProduct = cusService.updateProductQuantity(productName);

	  return new ResponseEntity<Product>(updatedProduct,HttpStatus.ACCEPTED);
		
    }
	
	@DeleteMapping("/product/{productId}")
	public  ResponseEntity<Product> deleteProductHandler(@PathVariable("productId") Integer productId){
		  Product deletedProduct =prodService.deleteProduct(productId);
		  return new ResponseEntity<Product>(deletedProduct,HttpStatus.OK);
		  
	}
	
	
	
	
	
	@PostMapping("/removeCart/{productName}/{mobile}")
	public ResponseEntity<String> removeProductFromCart(@PathVariable("productName") String productName,
			                                            @PathVariable("mobile") String mobile){
								
		String message =  cusService.removeProductFromCart(productName, mobile);
		
		 return new ResponseEntity<String>(message,HttpStatus.ACCEPTED);
		
		
	}
	
	
	
	
	                            //Harshit //
	
	@GetMapping("/Phelp")
	public String getTest() {
		return "Jay Shri Krishna";
	}
	
	
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

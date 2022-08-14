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
	
	
	
	@PostMapping("/product")
    public ResponseEntity<Product> addProductHandler(@Valid @RequestBody Product product){
  
	   Product newProduct = prodService.addProduct(product);

	  return new ResponseEntity<Product>(newProduct,HttpStatus.CREATED);
		
    }
	
}

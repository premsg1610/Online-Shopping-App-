package com.masai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Customer;
import com.masai.service.CustomerService;



@RestController
@RequestMapping("/customer")
public class CustomerController {
    
	@Autowired
	private CustomerService cusService;

	
	
	@PostMapping("/")
     public ResponseEntity<Customer> registerCustomerHandler(@RequestBody Customer customer){
    	 
	   Customer newCustomer = cusService.registerCustomer(customer);
	   
	  return new ResponseEntity<>(newCustomer,HttpStatus.CREATED);
		
     }
	
	
	
	
	@DeleteMapping("/{customerId}/")
    public ResponseEntity<Customer> deleteCustomerHandler(@PathVariable ("cutomerId") Integer customerId){
   	 
	   Customer deletedCustomer = cusService.deleteCustomerById(customerId);
	   
	  return new ResponseEntity<>(deletedCustomer,HttpStatus.OK);
		
    }
	
	
	
	
	@PutMapping("/{customerId}/")
    public ResponseEntity<Customer> updateCustomerHandler(@PathVariable("customerId") Integer customerId , @RequestBody Customer customer){
   	 
	   Customer updatedCustomer = cusService.updateCustomerById(customerId,customer);
	   
	  return new ResponseEntity<>(updatedCustomer,HttpStatus.ACCEPTED);
		
    }
	
	
	
	
	
	
}

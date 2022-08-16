package com.masai.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Admin;
import com.masai.model.Product;
import com.masai.service.AdminServiceImpl;
import com.masai.service.ProductService;

@RestController
public class AdminController {
	
	@Autowired
	private AdminServiceImpl aService;

	
	
	@GetMapping("/help")
	public String getTest() {
		return "Hello, How can I help you";
	}

	
	

	/*
	@GetMapping("/getAdmin/{mobile}")
	public Admin getAdminDetailsHandler(@PathVariable("mobile") String mobile)
	{
		return adminServiceImpl.getAdminDetails(mobile);
*/
	
	@PostMapping("/addAdmin")
	public Admin registerCustomer(@RequestBody Admin admin) {

		return aService.createAdmin(admin);


	}
	

	// To update existing user details by passing its login key
	@PutMapping(value = "/updateAdmin")
	public Admin updateCustomer(@RequestBody Admin admin, @RequestParam(required = false) String key) {
		return aService.updateAdmin(admin, key);
	}

	
	
	// To delete existing user details by passing its login key
	@DeleteMapping(value = "/deleteAdmin")
	public Admin deleteCustomer(@RequestParam(required = false) String key) {
		return aService.deleteAdmin(key);
	}

	
	
	// To get details of current user by passing its login key
	@GetMapping(value = "/getAdmin")
	public Admin getCustomerDetails(@RequestParam(required = false) String key) {
		return aService.getAdminDetails(key);
	}
	
	
	
	
	
	
	
	
	
//	---------------------------------------Product Controller In Admin--------------------------------------
	
	
	@Autowired
	private ProductService pService;
	
	
	@PostMapping("/addProduct")
	public Product addProduct(@RequestBody Product product,@RequestParam String key)
	{
		return pService.addProduct(product,key);
	}
	
	@GetMapping("/getAllProducts")
	public List<Product> getAllProducts(@RequestParam String key)
	{
		return pService.getProductList(key);
	}
	
	@GetMapping("/getProduct/{name}")
	public Product getProductByName(@PathVariable String name,@RequestParam String key)
	{
		return pService.getProductByName(name,key);
	}
	
	@PutMapping("/updateProduct")
	public Product updateProduct(@RequestBody Product product,@RequestParam String key)
	{
		return pService.updateProduct(product,key);
	}
	
	@DeleteMapping("/deleteProduct/{name}")
	public Product deleteProductByName(@PathVariable String name,@RequestParam String key)
	{
		return pService.deleteProductByName(name,key);
	}
	
	
	
	
	
	
	
		
//	++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//	|                            Customers Control By Admin                                    |
//	++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	@GetMapping("/getCustomers")
	public List<Customer> getCustomers(@RequestParam String key)
	{
		return aService.getCustomers(key);
	}
	
	@GetMapping("/getCustomer")
	public Customer getCustomer(@PathVariable String mobile,@RequestParam String key)
	{
		return aService.getCustomerByMobile(mobile, key);
	}
	
	
	
}

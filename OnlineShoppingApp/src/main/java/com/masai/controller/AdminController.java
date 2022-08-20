package com.masai.controller;



import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Admin;
import com.masai.model.Customer;
import com.masai.model.Product;
import com.masai.service.AdminServiceImpl;
import com.masai.service.ProductService;

@RestController
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminServiceImpl aService;

	
	
	@PostMapping("/")
	public Admin registerAdminHandler(@Valid @RequestBody Admin admin) {

		return aService.registerAdmin(admin);


	}
	

	// To update existing user details by passing its login key
	@PutMapping(value = "/")
	public Admin updateAdminHandler(@Valid @RequestBody Admin admin, @RequestParam(required = false) String key) {
		return aService.updateAdmin(admin, key);
	}

	
	
	// To delete existing user details by passing its login key
	@DeleteMapping(value = "/")
	public Admin deleteAdminHandler(@RequestParam(required = false) String key) {
		return aService.deleteAdmin(key);
	}

	
	
	// To get details of current user by passing its login key
	@GetMapping(value = "/")
	public Admin getAdminDetailsHandler(@RequestParam(required = false) String key) {
		return aService.getAdminDetails(key);
	}
	
	
	
	
	
	
	
	
	
//	---------------------------------------Product Controller In Admin--------------------------------------
	
	
	@Autowired
	private ProductService pService;
	
	
	@PostMapping("/product")
	public Product addProductHandler(@Valid @RequestBody Product product,@RequestParam String key)
	{
		return pService.addProduct(product,key);
	}
	
//	@GetMapping("/getAllProducts")
//	public List<Product> getAllProducts(@RequestParam String key)
//	{
//		return pService.getProductList(key);
//	}
//	
//	@GetMapping("/getProduct/{name}")
//	public Product getProductByName(@PathVariable String name,@RequestParam String key)
//	{
//		return pService.getProductByName(name,key);
//	}
	
	@PutMapping("/product")
	public Product updateProductHandler(@Valid @RequestBody Product product,@RequestParam String key)
	{
		return pService.updateProduct(product,key);
	}
	
	@DeleteMapping("/product/{name}")
	public Product deleteProductByNameHandler(@PathVariable String name,@RequestParam String key)
	{
		return pService.deleteProductByName(name,key);
	}
	
	
	
	
	
	
	
		
//	++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//	|                            Customers Control By Admin                                    |
//	++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	@GetMapping("/customers")
	public List<Customer> getAllCustomersDetailsHandler(@RequestParam String key)
	{
		return aService.getCustomers(key);
	}
	
	@GetMapping("/customer/{mobile}")
	public Customer getCustomerDetailsHandler(@PathVariable String mobile,@RequestParam String key)
	{
		return aService.getCustomerByMobile(mobile, key);
	}
	
	
	
}

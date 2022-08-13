package com.masai.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.Admins;
import com.masai.service.AdminServiceImpl;

@RestController
public class AdminController {
	
	@Autowired
	private AdminServiceImpl adminServiceImpl;
	
	
	//to register admin
	@PostMapping(value = "/saveAdmin",consumes = MediaType.APPLICATION_JSON_VALUE)
	public Admins saveAdminHandler(@RequestBody Admins admin)
	{
		return adminServiceImpl.createAdmin(admin);
	}
	
	@GetMapping
	public Admins getAdminDetailsHandler(@PathVariable String email)
	{
		return adminServiceImpl.getAdminDetails(email);
	}
	
	// to update existing admin details by passing its login key
	@PutMapping(value = "/updateAdmin")
	public Admins updateAdminHandler(@RequestBody Admins admin, @RequestParam(required = false) String key)
	{
		return adminServiceImpl.updateAdmin(admin, key);
	}
	
	
	
	
	
	
}

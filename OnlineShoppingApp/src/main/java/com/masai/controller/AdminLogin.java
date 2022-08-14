package com.masai.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.AdminCurrentUserSessionDTO;
import com.masai.model.AdminDTO;
import com.masai.service.AdminLogInImpl;

@RestController
public class AdminLogin {

	
	@Autowired
	private AdminLogInImpl adminLogIn;

	// for user Login
	@PostMapping(value = "/login")
	public AdminCurrentUserSessionDTO logInCustomer(@Valid @RequestBody AdminDTO adminDTO) {
		return adminLogIn.logIntoAccount(adminDTO);
	}
	
	// for user Logout
	@PatchMapping(value = "/logout")
	public String logOutCustomer(@RequestParam(required = false) String key) {
		return adminLogIn.logOutFromAccount(key);
	}

	
}

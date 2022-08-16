package com.masai.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.model.CurrentUserSession;
import com.masai.model.CustomerDTO;
import com.masai.service.CustomerLogInImpl;

@RestController
public class CustomerLogin {

	
	@Autowired
	private CustomerLogInImpl customerLogIn;

	// for user Login
	@PostMapping(value = "/Customerlogin")
	public CurrentUserSession logInCustomer(@Valid @RequestBody CustomerDTO customerDTO) {
		return customerLogIn.logIntoAccount(customerDTO);
	}
	
	// for user Logout
	@PatchMapping(value = "/Customerlogout")
	public String logOutCustomer(@RequestParam(required = false) String key) {
		return customerLogIn.logOutFromAccount(key);
	}
	
}

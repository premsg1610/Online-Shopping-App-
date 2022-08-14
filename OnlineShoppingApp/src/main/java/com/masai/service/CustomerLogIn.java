package com.masai.service;

import com.masai.model.CurrentUserSession;
import com.masai.model.CustomerDTO;

public interface CustomerLogIn {
	 public CurrentUserSession logIntoAccount(CustomerDTO customerDTO);
		
		public String logOutFromAccount(String key);
}

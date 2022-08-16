package com.masai.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.LoginException;
import com.masai.model.CurrentUserSession;
import com.masai.model.Customer;
import com.masai.model.CustomerDTO;
import com.masai.repository.CustomerDao;
import com.masai.repository.CustomerLoginSessionDAO;
import com.masai.util.GetCurrentLoginUserSessionDetailsImpl;

import net.bytebuddy.utility.RandomString;


@Service
public class CustomerLogInImpl implements CustomerLogIn {

	
	@Autowired
	private CustomerDao customerDAO;
	
	@Autowired
	private CustomerLoginSessionDAO sessionDAO;
	
	@Autowired
	private GetCurrentLoginUserSessionDetailsImpl getCurrentLoginUser;
	
	
	
	@Override
	public CurrentUserSession logIntoAccount(CustomerDTO customerDTO) {
		
		Customer opt = customerDAO.findByMobile(customerDTO.getMobile());
		
		if(opt == null) {
			throw new LoginException("Please Enter Valid Mobile Number");
		}
		
		
		Integer customerId =opt.getCustomerId();
		
		Optional<CurrentUserSession> currentUserOptional = sessionDAO.findByCustomerId(customerId);

		
		if(currentUserOptional.isPresent()) {
			throw new LoginException("User already logged in with this number");
		}
		
		if(opt.getPassword().equals(customerDTO.getPassword())) {
			
			String key = RandomString.make(8);
			
			CurrentUserSession currentUserSession = new CurrentUserSession(customerId, key,  LocalDateTime.now());
			sessionDAO.save(currentUserSession);

			return currentUserSession;
		}
		else {
			throw new LoginException("Please Enter Valid Password");
		}
	}
	
	
	
	@Override
	public String logOutFromAccount(String key) {
		
		Optional<CurrentUserSession> currentUserOptional = sessionDAO.findByUuid(key);
		
		if(!currentUserOptional.isPresent()) {
			throw new LoginException("User is not logged in with this number");
		}
		
		CurrentUserSession currentUserSession = getCurrentLoginUser.getCurrentUserSession(key);
		sessionDAO.delete(currentUserSession);
		
		return "Logged Out...";
	}
	
	
}

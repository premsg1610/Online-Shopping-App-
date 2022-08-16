package com.masai.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.AdminException;
import com.masai.model.Admin;
import com.masai.model.AdminCurrentUserSession;
import com.masai.model.AdminCurrentUserSessionDTO;
import com.masai.model.AdminDTO;
import com.masai.repository.AdminDao;
import com.masai.repository.AdminSessionDao;
import com.masai.util.GetCurrentLoginAdminSessionDetailsImpl;

import net.bytebuddy.utility.RandomString;

@Service
public class AdminLogInImpl implements AdminLogIn{

	
	@Autowired
	private AdminDao adminDAO;
	
	@Autowired
	private AdminSessionDao sessionDAO;
	
	@Autowired
	private GetCurrentLoginAdminSessionDetailsImpl getCurrentLoginUser;
	
	
	
	@Override
	public AdminCurrentUserSessionDTO logIntoAccount(AdminDTO adminDTO) {
		
		Admin opt = adminDAO.findByMobile(adminDTO.getMobile());
		
		if(opt == null) {
			throw new AdminException("Please Enter Valid Mobile Number");
		}
		
		
		Integer adminId = opt.getAdminId();
		
		Optional<AdminCurrentUserSession> currentUserOptional = sessionDAO.findByAdminId(adminId);
		
		
		
//		Customer newCustomer = opt.get();
//		Integer customerId = newCustomer.getCustomerId();
//		Optional<CurrentUserSession> currentUserOptional = sessionDAO.findByCustomerId(customerId);
		
		if(currentUserOptional.isPresent()) {
			throw new AdminException("User already logged in with this number");
		}
		
		if(opt.getPassword().equals(adminDTO.getPassword())) {
			
			String key = RandomString.make(6);
			
			AdminCurrentUserSession currentUserSession = new AdminCurrentUserSession(adminId, key,  LocalDateTime.now());
			sessionDAO.save(currentUserSession);
			
			
			return new AdminCurrentUserSessionDTO(adminId,key,LocalDateTime.now());
		}
		else {
			throw new AdminException("Please Enter Valid Password");
		}
	}
	
	
	
	@Override
	public String logOutFromAccount(String key) {
		
		Optional<AdminCurrentUserSession> currentUserOptional = sessionDAO.findByUuid(key);
		
		if(!currentUserOptional.isPresent()) {
			throw new AdminException("User is not logged in with this number");
		}
		
		AdminCurrentUserSession currentUserSession = getCurrentLoginUser.getCurrentUserSession(key);
		sessionDAO.delete(currentUserSession);
		
		return "Logged Out...";
	}

}

package com.masai.util;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.masai.model.Admin;
import com.masai.model.AdminCurrentUserSession;
import com.masai.repository.AdminDao;
import com.masai.repository.AdminSessionDao;



@Component
public class GetCurrentLoginAdminSessionDetails {

	
	@Autowired
	private AdminSessionDao sessionDAO;

	@Autowired
	private AdminDao adminDAO;
	
	public AdminCurrentUserSession getCurrentUserSession(String key) {
		Optional<AdminCurrentUserSession> optional = sessionDAO.findByUuid(key);
		
		return optional.get();
	}
	
	public Integer getCurrentUserSessionId(String key) {
		Optional<AdminCurrentUserSession> optional = sessionDAO.findByUuid(key);
		
		return optional.get().getId();
	}
	
	public Admin getCurrentCustomer(String key) {
		Optional<AdminCurrentUserSession> optional = sessionDAO.findByUuid(key);
		
		Integer adminId = optional.get().getId();
		
		return  adminDAO.getById(adminId);
	}
	
	
}

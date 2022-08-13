package com.masai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.AdminException;
import com.masai.model.Admins;

import com.masai.repository.AdminDao;

@Service
public class AdminServiceImpl  implements AdminService{
	
	@Autowired
	private AdminDao adminDao;     // Dao interface
	

	@Override
	public Admins createAdmin(Admins admin) {                      // for register a admin
		
		Optional<Admins> a = adminDao.findById(admin.getAdminId());
		if(!a.isPresent())
		{
			return adminDao.save(admin);
			
		}
		else {
			throw new AdminException("I found exception in admin");
		}
		
	}
	
	
	@Override
	public Admins getAdminDetails(String mobile) {
		
	return null;
	}

	@Override
	public Admins updateAdmin(Admins userAdmin ,String key) {            // for update admin
		return null;
		
	}

	


	

}

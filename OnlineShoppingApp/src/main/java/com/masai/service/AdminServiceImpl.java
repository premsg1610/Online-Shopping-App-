package com.masai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.AdminException;
import com.masai.model.Admin;

import com.masai.repository.AdminDao;

@Service
public class AdminServiceImpl  implements AdminService{
	
	@Autowired
	private AdminDao adminDao;     // Dao interface
	

	@Override
	public Admin createAdmin(Admin admin) {                      // for register a admin
		
		Admin a = adminDao.findByMobile(admin.getMobile());
		if(a==null)
		{
			return adminDao.save(admin);
		}
		else {
			throw new AdminException("The user is already present in the database");
		}
		
	}
	
	
	@Override
	public Admin getAdminDetails(String mobile) {
		Admin a = adminDao.findByMobile(mobile);
		if(a==null)
		{
			throw new AdminException("The user is already present in the database");
		}
		else {
			return a;
		}
		
	}

	@Override
	public Admin updateAdmin(Admin userAdmin ,String key) {            // for update admin
		return null;
		
	}

	


	

}

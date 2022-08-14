package com.masai.service;

import com.masai.model.Admin;

public interface AdminService {
	
    public Admin createAdmin(Admin admin);
	
	public Admin getAdminDetails(String email);
	
	public Admin updateAdmin(Admin userAdmin, String email);

}

package com.masai.service;

import com.masai.model.Admins;

public interface AdminService {
	
    public Admins createAdmin(Admins admin);
	
	public Admins getAdminDetails(String email);
	
	public Admins updateAdmin(Admins userAdmin, String email);

}

package com.masai.service;

import java.util.List;

import com.masai.model.Admin;
import com.masai.model.Customer;

public interface AdminService {
	
    public Admin registerAdmin(Admin admin);
	
    public Admin updateAdmin(Admin admin, String key);
	
	public Admin deleteAdmin(String key);
	
	public Admin getAdminDetails(String key);
	
	public List<Customer> getCustomers(String key);
	
	public Customer getCustomerByMobile(String mobile, String key);
	

}

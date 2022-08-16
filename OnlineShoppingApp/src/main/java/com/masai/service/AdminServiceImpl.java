package com.masai.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.AdminException;
import com.masai.exceptions.CustomerException;
import com.masai.model.Admin;
import com.masai.model.Customer;
import com.masai.repository.AdminDao;
import com.masai.repository.CustomerDao;
import com.masai.util.GetCurrentLoginAdminSessionDetailsImpl;

@Service
public class AdminServiceImpl  implements AdminService{
	
	@Autowired
	private AdminDao adminDAO;
	
	@Autowired
	private CustomerDao customerDAO;
	
	@Autowired
	private GetCurrentLoginAdminSessionDetailsImpl getCurrentLoginUser;

	@Override
	public Admin createAdmin(Admin admin) {
		Admin oldAdmin = adminDAO.findByMobile(admin.getMobile());
		
		if(oldAdmin==null)
		{
			return adminDAO.save(admin);
		}
		 throw new AdminException("Admin does not exist");
		
	}

	@Override
	public Admin updateAdmin(Admin admin, String key) {
		Admin customer2 = getCurrentLoginUser.getCurrentAdmin(key);	
		
		if(customer2.getMobile().equals(admin.getMobile()))
		{
			customer2.setFirstName(admin.getFirstName());
			customer2.setLastName(admin.getLastName());
			customer2.setEmail(admin.getEmail());
			customer2.setPassword(admin.getPassword());
			return admin;
		}
		else{
			throw new AdminException("No user found.. try login first");
		}
	}

	@Override
	public Admin deleteAdmin(String key) {
		Admin admin = getCurrentLoginUser.getCurrentAdmin(key);		
		adminDAO.delete(admin);
		return admin;
	}

	@Override
	public Admin getAdminDetails(String key) {
		Admin admin = getCurrentLoginUser.getCurrentAdmin(key);	
		System.out.println(admin);
		return admin;
	}
	
	
//      +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
//	|                             Customers                                               |
//	+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	
	
	
	@Override
	public List<Customer> getCustomers(String key) {
		Admin admin = getCurrentLoginUser.getCurrentAdmin(key);	
		if(admin!=null)
		{
			List<Customer> customers = customerDAO.findAll();
			
			if(!customers.isEmpty())
			{
				return customers;
			}
			throw new CustomerException("There is no customer please add customers");
			
		}
		else {
			throw new AdminException("The admin is not logged in");
		}
	}

	@Override
	public Customer getCustomerByMobile(String mobile, String key) {
		Admin admin = getCurrentLoginUser.getCurrentAdmin(key);	
		if(admin!=null)
		{
			Customer customer = customerDAO.findByMobile(mobile);
			
			if(customer!=null)
			{
				return customer;
			}
			throw new CustomerException("There is no customer please add customers");
			
		}
		else {
			throw new AdminException("The admin is not logged in");
		}
	}



	

}

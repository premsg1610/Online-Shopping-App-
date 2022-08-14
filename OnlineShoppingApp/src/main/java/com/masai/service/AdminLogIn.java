package com.masai.service;

import com.masai.model.AdminCurrentUserSessionDTO;
import com.masai.model.AdminDTO;

public interface AdminLogIn {

    public AdminCurrentUserSessionDTO logIntoAccount(AdminDTO adminDTO);
	
	public String logOutFromAccount(String key);
	
}

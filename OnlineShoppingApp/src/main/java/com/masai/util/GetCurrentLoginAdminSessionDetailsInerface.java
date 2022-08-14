package com.masai.util;

import com.masai.model.Admin;
import com.masai.model.AdminCurrentUserSession;

public interface GetCurrentLoginAdminSessionDetailsInerface {

public AdminCurrentUserSession getCurrentUserSession(String key);
	
	public Integer getCurrentUserSessionAdminId(String key);
	
	public Admin getCurrentAdmin(String key);
	
	
}

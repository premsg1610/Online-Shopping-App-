package com.masai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.masai.model.AdminCurrentUserSession;



public interface AdminSessionDao extends JpaRepository<AdminCurrentUserSession, Integer>{

	
	public Optional<AdminCurrentUserSession>  findByAdminId(Integer adminId);
	
	public Optional<AdminCurrentUserSession>  findByUuid(String uuid);
	
}

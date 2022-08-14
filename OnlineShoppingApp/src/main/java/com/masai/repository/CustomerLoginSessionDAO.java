package com.masai.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.masai.model.CurrentUserSession;


@Repository
public interface CustomerLoginSessionDAO extends JpaRepository<CurrentUserSession, Integer> {

    public Optional<CurrentUserSession>  findByCustomerId(Integer customberId);
	
	public Optional<CurrentUserSession>  findByUuid(String uuid);
	
	
}

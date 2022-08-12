package com.masai.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;



@Entity
public class Admin extends User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer adminId;
	private String firstName;
	private String lastName;
	private String mobile;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Product> productList = new ArrayList<>(); 
	
}

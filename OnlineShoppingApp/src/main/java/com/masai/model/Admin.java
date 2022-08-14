package com.masai.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor


@Entity
public class Admin {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer adminId;
	
//	@NotNull(message = "Admin first-Name can not be null")
	private String firstName;
	private String lastName;
	
	@Pattern(regexp="[6-9]\\d{9}", message = "Only Valid for 10 digit indian phone number")
	private String mobile;
	private String password;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Product> productList; 
	
}

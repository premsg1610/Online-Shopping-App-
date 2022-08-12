package com.masai.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor


@Entity
public class Customer extends User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO )
    private Integer customerId;
	private String firstName;
	private String lastName;
	private Integer mobile;
	private String password;
	private String email;
	
	@OneToOne
	Address address;
}

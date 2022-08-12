package com.masai.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

@Entity
public class Customer extends User {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO )
	String customerId;
	String firstName;
	String lastName;
	Integer mobile;
	String email;
	
	@Embedded
	Address address;
}

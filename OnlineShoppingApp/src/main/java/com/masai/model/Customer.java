package com.masai.model;

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


public class Customer {

	String customerId;
	String firstName;
	String lastName;
	Integer mobile;
	Address address;
	String email;
}

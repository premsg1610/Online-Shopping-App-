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

public class Address {

	Integer addressId;
	String streetNo;
	String buildingName;
	String city;
	String state;
	String country;
	String pincode;
	
}

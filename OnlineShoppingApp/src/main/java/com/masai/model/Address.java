package com.masai.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

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

//	private Integer addressId;
	
	@NotNull(message = "streetNo cannot be null")
	private String streetNo;
	
	@NotNull(message = "buildingName cannot be null")
	private String buildingName;
	
	@NotNull(message = "city cannot be null")
	private String city;
	
	@NotNull(message = "state cannot be null")
	private String state;
	
	@NotNull(message = "country cannot be null")
	private String country;
	
	@NotNull
	@Pattern(regexp="[0-9]{6}", message = "Only Valid for 6 digit indian pincode")
	private String pincode;
	
}

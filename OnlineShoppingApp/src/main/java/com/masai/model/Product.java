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


public class Product {

	String productId;
	String productName;
	Double price;
	String color;
	String dimension;
	String specification;
	String manufacturer;
	Integer quantity;
	Category category;
	
	
}

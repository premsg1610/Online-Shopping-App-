package com.masai.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Embedded;

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



public class Cart {

	private String cartId;
	
	@Embedded
	private Customer customer;
	
	@Embedded
	@ElementCollection
	private List<Product> products;
}

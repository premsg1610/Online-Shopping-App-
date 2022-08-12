package com.masai.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

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


public class Order {

	private String orderId;
	private LocalDate orderDate;
	private String orderStatus;
	
	@Embedded
	private Customer customer;
	
	@Embedded
	@ElementCollection
	private List<Product> productlist;
	
	@Embedded
	private Address address;
	
	
}

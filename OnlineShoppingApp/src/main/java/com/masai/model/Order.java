package com.masai.model;

import java.time.LocalDate;
import java.util.Set;

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

	String orderId;
	LocalDate orderDate;
	String orderStatus;
	Customer customer;
	Set<Product> productlist;
	Address address;
	
	
}

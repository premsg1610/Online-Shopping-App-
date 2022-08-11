package com.masai.model;

import java.time.LocalDate;
import java.util.Set;

public class Order {

	String orderId;
	LocalDate orderDate;
	String orderStatus;
	Customer customer;
	Set<Product> productlist;
	Address address;
	
	
}

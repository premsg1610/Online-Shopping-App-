package com.masai.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

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
public class Product {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer productId;
	
	@NotNull(message = "Product name cannot be empty")
	private String productName;
	
	
	@Min(value = 1, message = "Product price should not be 0")
	private Double price;
	
	@NotNull(message = "color information should be there")
	private String color;
	
	@NotNull(message = "Product should have specification")
	private String specification;
	
	@NotNull(message="manufacturer details should be there")
	private String manufacturer;
	
	@Min(value = 0, message = "Minimum quantity should be 1")
	private Integer quantity;
	
	@NotNull(message = "Category cannot be empty")
	@OneToOne(cascade=CascadeType.ALL)
	private Category category;
	
	
	
}

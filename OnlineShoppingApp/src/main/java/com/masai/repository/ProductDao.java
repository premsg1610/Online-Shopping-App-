package com.masai.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.masai.model.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Integer> {

	public Product findByProductName(String productName);
	
	@Query("delete from Product p where p.productName=?1")
	public String deleteProductByName(String productName);
}

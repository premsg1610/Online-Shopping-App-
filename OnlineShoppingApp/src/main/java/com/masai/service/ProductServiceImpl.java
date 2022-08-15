package com.masai.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.ProductException;
import com.masai.model.Product;
import com.masai.repository.ProductDao;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao prodDao;
	

	@Override
	public Product addProduct(Product product) {
		
		Product product1 =  prodDao.findByProductName(product.getProductName());
		
		if(product1 == null)
		{
			return prodDao.save(product);
		}
		else {
			product1.setQuantity(product1.getQuantity()+product.getQuantity());

			return prodDao.save(product1);
		}
	}
	
	@Override
	public Product deleteProduct(Integer productId) {
		
       Optional<Product> product1 =  prodDao.findById(productId);
		
		if(!product1.isPresent())
		{
			throw new ProductException("Product Not Found");
		}
		prodDao.delete(product1.get());
		
	return	product1.get();
	}


	

		
}


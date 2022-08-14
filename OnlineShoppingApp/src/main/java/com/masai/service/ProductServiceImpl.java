package com.masai.service;

import java.util.List;

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
			return product1;
		}
	}

	@Override
	public Product updateProduct(Product product) {
        Product product1 =  prodDao.findByProductName(product.getProductName());
		
		if(product1 == null)
		{
			return prodDao.save(product);
		}
		else {
			product1.setPrice(product1.getPrice()+product.getPrice());
			return product1;
		}
	}

	@Override
	public List<Product> getProductList() {
		List<Product> productList = prodDao.findAll();
		
		if(productList.isEmpty())
		{
			throw new ProductException("There is no product in the list please add the products");
		}
		return productList;
	}

	@Override
	public Product getProductByName(String name) {
		 Product product1 =  prodDao.findByProductName(name);
		 if(product1==null)
		 {
			 throw new ProductException("Product not with this name "+name);
		 }
		 return product1;
	}

	@Override
	public Product deleteProductByName(String name) {
		 Product product1 =  prodDao.findByProductName(name);
		 if(product1==null)
		 {
			 throw new ProductException("Product not with this name "+name);
		 }
		 prodDao.delete(product1);
		 return product1;
	}

}

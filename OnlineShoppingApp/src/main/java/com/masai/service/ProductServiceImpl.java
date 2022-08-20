package com.masai.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exceptions.AdminException;
import com.masai.exceptions.ProductException;
import com.masai.model.Admin;
import com.masai.model.Product;
import com.masai.repository.ProductDao;
import com.masai.util.GetCurrentLoginAdminSessionDetailsImpl;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao prodDao;
	
	@Autowired
	private GetCurrentLoginAdminSessionDetailsImpl getCurrentLoginUser;

	@Override
	public Product addProduct(Product product,String key) {
		
		
        Admin customer2 = getCurrentLoginUser.getCurrentAdmin(key);	
		
		if(customer2!=null)
		{
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
		else {
			throw new AdminException("The admin is not logged in");
		}
		
	}
	
	@Override
	public Product updateProduct(Product product,String key) {
		
		 Admin admin = getCurrentLoginUser.getCurrentAdmin(key);
		 if(admin!=null)
		 {
			 Product product1 =  prodDao.findByProductName(product.getProductName());
				
				if(product1 == null)
				{
					return prodDao.save(product);
				}
				else {
					product1.setPrice(product.getPrice());
					return prodDao.save(product1);
				}
		 }
		 else {
				throw new AdminException("The admin is not logged in");
			}
		 
		

	}

//	@Override
//	public List<Product> getProductList(String key) {
//		
//		Admin admin = getCurrentLoginUser.getCurrentAdmin(key);
//		if (admin != null) {
//			List<Product> productList = prodDao.findAll();
//
//			if (productList.isEmpty()) {
//				throw new ProductException("There is no product in the list please add the products");
//			}
//			return productList;
//		} else {
//			throw new AdminException("The admin is not logged in");
//		}
//	}

//	@Override
//	public Product getProductByName(String name,String key) {
//		
//		 Admin admin = getCurrentLoginUser.getCurrentAdmin(key);
//		 if(admin!=null)
//		 {
//		
//		 Product product1 =  prodDao.findByProductName(name);
//		 if(product1==null)
//		 {
//			 throw new ProductException("Product not with this name "+name);
//		 }
//		 return product1;
//		 }
//		 else {
//			 throw new AdminException("The admin is not logged in");
//		 }
//	}

	
	
	@Override
	public Product deleteProductByName(String name,String key) {
		
		Admin admin = getCurrentLoginUser.getCurrentAdmin(key);
		 if(admin!=null)
		 {
		
		 Product product1 =  prodDao.findByProductName(name);
		 if(product1==null)
		 {
			 throw new ProductException("Product not found with name "+name);
		 }
		 prodDao.delete(product1);
		 return product1;
		 }
		 else {
			 throw new AdminException("The admin is not logged in");
		 }
	}
	
	
	
	
	@Override
	public Product getProductDetailsByName(String productName) {
		
			 Product product =  prodDao.findByProductName(productName);
			 if(product == null)
			 {
				 throw new ProductException("Product not found with name "+ productName);
			 }
			 return product ;
			 
	}
		 

	

	@Override
	public List<Product> getAllProductDetails() {
		
			List<Product> productList = prodDao.findAll();

			if (productList.isEmpty()) {
				throw new ProductException("No product exist in this shopping site.");
			}
			return productList;
		} 
	


		
}


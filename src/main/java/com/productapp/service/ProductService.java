package com.productapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productapp.entity.Product;
import com.productapp.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> findAllProduct() {
		return productRepository.findAll();
	}
	
	public Product getProductById(Long id) {
		Optional<Product> productStatus = productRepository.findById(id);
		Product product = null;
		if ( productStatus.isPresent() ) {
			product = productStatus.get();
		}
		return product;
	}
	
	public boolean getProductByCode(Long code) {
		return productRepository.existsProductByProductCode(code);
	}
	
	public void saveProduct(Product product) {
		productRepository.save(product);
	}
	
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
	
	public long counts() {
		return productRepository.count();
	}
	
}

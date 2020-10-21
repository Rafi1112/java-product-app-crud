package com.productapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.productapp.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
//	@Query("select case when count(c)> 0 then true else false end from Product c where c.productCode = :productCode")
	public boolean existsProductByProductCode(Long code);
	
}

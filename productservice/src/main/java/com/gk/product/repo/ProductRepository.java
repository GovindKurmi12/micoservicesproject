package com.gk.product.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gk.product.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}

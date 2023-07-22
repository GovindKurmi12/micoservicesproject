package com.gk.product.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gk.product.model.Product;
import com.gk.product.repo.ProductRepository;
import com.gk.product.service.external.call.ProductExternalService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductExternalService productExternalService;

	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

	public List<Product> createAllProduct(List<Product> products) {
		List<Product> distictInventory = products.stream().filter(distinctByKey(Product::getProductName)).toList();
		return productRepository.saveAll(distictInventory);
	}

	public static <T> Predicate<T> distinctByKey(Function<? super T, String> keyExtractor) {
		Map<String, Boolean> seen = new ConcurrentHashMap<>();
		return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
	}

	public static final String PRODUCTSERVICE="productService";
	
	@CircuitBreaker(name = "PRODUCTSERVICE",fallbackMethod = "getPrice")
	public Double totalStockPrice(Long id) {
		Long totalStock=productExternalService.totalStockPrice(id);
		log.info("call external service successfully "+id);
		Optional<Product> product=findById(id);
		log.info("call Product entity data"+id);
	    return product.map(s->s.getPrice()*totalStock).orElseThrow();
	}

	public boolean checkProductExist(Long id) {
		return productRepository.existsById(id);
	}
	
	public Double getPrice(Exception e){
		return 0.0;
	}

	private Optional<Product> findById(long id) {
		return productRepository.findById(id);
	}

	public List<Product> getAllEmployee() {
		return productRepository.findAll();
	}

	public List<Product> sortedEmployee(String fields, int min, int max) {
		Sort sort=Sort.by(fields);
		Pageable pageable=PageRequest.of(min, max,sort);
		return productRepository.findAll(pageable).toList();
	}

}

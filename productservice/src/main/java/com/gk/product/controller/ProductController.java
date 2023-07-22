package com.gk.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.gk.product.model.Product;
import com.gk.product.service.ProductService;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("product")
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @PostMapping("addallproduct")
    public List<Product> createAllProduct(@RequestBody List<Product> product) {
        return productService.createAllProduct(product);
    }

    @GetMapping("totalstockprice/{id}")
    public Double totalStockPrice(@PathVariable Long id) {
        return productService.totalStockPrice(id);
    }

    @GetMapping("checkExist/{id}")
    public boolean checkProductExist(@PathVariable Long id) {
        return productService.checkProductExist(id);
    }

    @GetMapping("getallproduct")
    public List<Product> getAllEmployee() {
        return productService.getAllEmployee();
    }

    @GetMapping("sorting")
    public List<Product> sortedEmployee(@RequestParam("fields") String fields, @RequestParam("min") int min,
                                        @RequestParam("max") int max) {
        return productService.sortedEmployee(fields, min, max);
    }

}

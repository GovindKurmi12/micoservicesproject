package com.gk.product.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product", schema = "productservice")
public class Product {

	@Id
	private Long id;
	private String productName;
	private double price;
	private String description;
	private String dateTime;

}

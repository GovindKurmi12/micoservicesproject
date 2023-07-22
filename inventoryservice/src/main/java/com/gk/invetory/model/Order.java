package com.gk.invetory.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

	private String customerName;

	private LocalDate orderDate;

	private BigDecimal totalPrice;
	
	private Address address;
	
	private String customerId;

	private List<Item> items;

}

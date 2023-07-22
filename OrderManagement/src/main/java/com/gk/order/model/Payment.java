package com.gk.order.model;

import lombok.Data;

@Data
public class Payment {

	private Long id;
	private double amount;
	private String customerId;
}

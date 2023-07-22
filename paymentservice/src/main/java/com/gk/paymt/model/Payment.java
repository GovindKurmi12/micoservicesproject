package com.gk.paymt.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "payment", schema = "paymentservice")
public class Payment {

	@Id
	private Long id;
	private double amount;
	private String customerId;
}

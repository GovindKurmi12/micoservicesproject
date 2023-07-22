package com.gk.paymt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gk.paymt.model.Payment;
import com.gk.paymt.model.Transaction;
import com.gk.paymt.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@PostMapping("addpayment")
	public List<Payment> addAllPayment(@RequestBody List<Payment> payments) {
		return paymentService.addAllPayment(payments);
	}

	@PostMapping("dopayment")
	public boolean doPayment(@RequestBody Payment payments) {
		return paymentService.doPayment(payments)!=null;
	}
	
	@GetMapping("gettransaction/{id}")
	public Transaction getTransaction(@PathVariable long id) {
		return paymentService.getTransaction(id);
	}
}

package com.gk.paymt.service;

import java.util.List;

import com.gk.paymt.model.Payment;
import com.gk.paymt.model.Transaction;

public interface PaymentService {

	List<Payment> addAllPayment(List<Payment> payments);

	Transaction doPayment(Payment payments);

	Transaction getTransaction(long id);

}

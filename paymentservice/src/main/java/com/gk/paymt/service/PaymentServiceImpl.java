package com.gk.paymt.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gk.paymt.exception.TranactionNotFound;
import com.gk.paymt.model.Payment;
import com.gk.paymt.model.Transaction;
import com.gk.paymt.repo.PaymentRepo;
import com.gk.paymt.repo.TransactionRepository;

import jakarta.transaction.Transactional;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepo paymentRepo;

	@Autowired
	private TransactionRepository transactionRepository;

	public List<Payment> addAllPayment(List<Payment> payments) {
		return paymentRepo.saveAll(payments);
	}

	@Transactional
	@Override
	public Transaction doPayment(Payment payments) {

		updateAmount(payments);
		Transaction transaction = Transaction.builder().amount(payments.getAmount())
				.customerId(payments.getCustomerId()).build();
		return transactionRepository.save(transaction);
	}

	private void updateAmount(Payment payment) {
		if (payment == null) {
			throw new IllegalArgumentException("Payment object cannot be null");
		}

		String customerId = payment.getCustomerId();
		if (customerId == null) {
			throw new IllegalArgumentException("Payment ID cannot be null");
		}

		Optional<Payment> existingPayment = getPaymentByCustomerId(customerId);
		if (existingPayment.isPresent()) {
			existingPayment.map(p -> {
				double newAmount = calculateNewAmount(p, payment.getAmount());
				p.setAmount(newAmount);
				return paymentRepo.save(p);
			});
		} else {
			throw new IllegalArgumentException("Payment not found for ID: " + customerId);
		}
	}

	private double calculateNewAmount(Payment existingPayment, double amountToSubtract) {
		return existingPayment.getAmount() - amountToSubtract;
	}

	public Optional<Payment> getPaymentByCustomerId(String customerId) {
		return paymentRepo.findByCustomerId(customerId);
	}

	@Override
	public Transaction getTransaction(long id) {
		
		return transactionRepository.findById(id).orElseThrow(()->new TranactionNotFound("this transaction details not found "+id));
	}
}
package com.gk.paymt.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gk.paymt.model.Payment;

public interface PaymentRepo extends JpaRepository<Payment, Long> {

	Optional<Payment> findByCustomerId(String customerId);

}

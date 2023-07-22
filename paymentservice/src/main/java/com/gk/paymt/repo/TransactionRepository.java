package com.gk.paymt.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gk.paymt.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}

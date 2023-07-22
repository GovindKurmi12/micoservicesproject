package com.gk.order.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.util.Streamable;

import com.gk.order.model.Order;

public interface OrderService  {

	List<Order> findAll();

	Optional<Order> findById(Long id);

	Streamable<Order> findAll(PageRequest of);

	Order save(Order order);

	void delete(Order order);

}

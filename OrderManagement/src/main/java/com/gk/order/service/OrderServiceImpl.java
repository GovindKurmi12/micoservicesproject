package com.gk.order.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import com.gk.order.externalService.CallExternalService;
import com.gk.order.model.Address;
import com.gk.order.model.Order;
import com.gk.order.repository.OrderRepository;
import com.gk.order.service.helpers.AddressHelper;

import jakarta.transaction.Transactional;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private CallExternalService callExternalService;
	
	@Autowired
	private AddressHelper addressHelper;

	@Override
	public List<Order> findAll() {
		return orderRepository.findAll();
	}

	@Override
	public Optional<Order> findById(Long id) {
		return orderRepository.findById(id);
	}

	@Override
	public Streamable<Order> findAll(PageRequest of) {
		return null;
	}

	@Transactional
	@Override
	public Order save(Order order) {
		Address existingAddress = addressHelper.checkAddressExist(order.getAddress());
		order.setAddress(existingAddress);
		order.setOrderDate(LocalDateTime.now());
		Order orderFlag = orderRepository.saveAndFlush(order);
		List<Integer> count = callExternalService.checkStock(order);
		double totalAmount = calculateTotalAmount(order,count);
	    callExternalService.doPayment(order.getCustomerId(), totalAmount);

		return orderFlag;

	}

	private double calculateTotalAmount(Order order,List<Integer> count) {
		double amount = 0;
		amount=IntStream.range(0, order.getItems().size()).filter(i->count.get(i)>0)
		.mapToDouble(m->order.getItems().get(m).getPrize()).sum();
		return amount;
	}

	@Override
	public void delete(Order order) {
		orderRepository.delete(order);
	}
}

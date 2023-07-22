package com.gk.order.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gk.order.model.Order;
import com.gk.order.service.OrderService;

import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
public class OrderController {

	private final OrderService orderService;

	@GetMapping("getallorder")
	public List<Order> getAllOrders() {
		return orderService.findAll();
	}

	@GetMapping("getbyorder/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
		Optional<Order> orderOptional = orderService.findById(id);
		return orderOptional.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
	}

	@GetMapping("/sort")
	public List<Order> sortById(@PathParam("one") int one, @PathParam("two") int two, @PathParam("name") String name) {
		Sort sort = Sort.by(name);
		return orderService.findAll(PageRequest.of(one, two, sort)).stream().toList();
	}

	@PostMapping("order")
	public ResponseEntity<String> createOrder(@RequestBody Order order) {
		if (orderService.save(order) != null) {
			return ResponseEntity.ok("Order Placed Successfull..!");
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Inventory service is down..!");
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order) {
		Optional<Order> orderOptional = orderService.findById(id);
		if (orderOptional.isPresent()) {
			Order updatedOrder = orderOptional.get();
			updatedOrder.setCustomerName(order.getCustomerName());
			updatedOrder.setOrderDate(order.getOrderDate());
			updatedOrder.setTotalPrice(order.getTotalPrice());
			return ResponseEntity.ok(orderService.save(updatedOrder));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
		Optional<Order> orderOptional = orderService.findById(id);
		if (orderOptional.isPresent()) {
			orderService.delete(orderOptional.get());
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.notFound().build();
		}
	}
}

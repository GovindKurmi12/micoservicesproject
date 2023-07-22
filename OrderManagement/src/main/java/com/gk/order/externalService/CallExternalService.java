package com.gk.order.externalService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.gk.order.model.Order;
import com.gk.order.model.Payment;

@Service
public class CallExternalService {

	@Autowired
	private WebClient client;

	public List<Integer> checkStock(Order order) {

		return client.post().uri("http://localhost:8082/inventory/checkproduct")
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters
				.fromValue(order))
				.retrieve()
				.bodyToFlux(Integer.class)
			    .collectList()
				.block();
	}

	public boolean doPayment(String customerId, double totalAmount) {
		Payment payment=new Payment();
		payment.setCustomerId(customerId);
		payment.setAmount(totalAmount);
		return client.post().uri("http://localhost:8084/payment/dopayment")
				.contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters
				.fromValue(payment))
				.retrieve()
				.bodyToMono(Boolean.class).block();		
	}
}

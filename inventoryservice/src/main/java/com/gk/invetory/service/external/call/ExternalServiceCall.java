package com.gk.invetory.service.external.call;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ExternalServiceCall {
	
	@Autowired
	private WebClient client;

	public boolean findProduct(Long id) {
		return client.get()
				.uri("http://localhost:8084/products/checkExist/{id}",id)
				.retrieve()
				.bodyToMono(Boolean.class)
				.block();
	}

}

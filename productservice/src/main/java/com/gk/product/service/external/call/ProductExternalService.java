package com.gk.product.service.external.call;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


@Component
public class ProductExternalService {
	
	@Autowired
	private WebClient client;
	

	public Long totalStockPrice(Long id) {

    return client.get()
		   .uri("http://localhost:8082/inventory/gettotalstock/{id}",id)
		   .retrieve()
		   .bodyToMono(Long.class)
		   .block();
	}
	
}

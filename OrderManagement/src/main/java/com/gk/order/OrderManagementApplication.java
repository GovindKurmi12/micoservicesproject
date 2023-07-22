package com.gk.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.reactive.function.client.WebClient;

@EnableTransactionManagement
@SpringBootApplication
public class OrderManagementApplication {
	public static void main(String[] args) {
		SpringApplication.run(OrderManagementApplication.class, args);
	}

    @Bean
    WebClient webFlux() {
		return WebClient.builder().build();
	}

}

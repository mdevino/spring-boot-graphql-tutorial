package com.mdevino.learning.spring.graphql.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import reactor.core.publisher.Flux;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerRepository repository;

	@QueryMapping
	Flux<Customer> customers() {
		return this.repository.findAll();
	}
	
}

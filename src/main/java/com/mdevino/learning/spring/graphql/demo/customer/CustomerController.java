package com.mdevino.learning.spring.graphql.demo.customer;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import com.mdevino.learning.spring.graphql.demo.order.Order;

import reactor.core.publisher.Flux;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerRepository repository;

	@QueryMapping
	Flux<Customer> customers() {
		return this.repository.findAll();
	}
	
	@QueryMapping
	Flux<Customer> customersByName(@Argument String name){
		return repository.findByName(name);
	}
	
	@SchemaMapping(typeName = "Customer")
	Flux<Order> orders(Customer customer){
		var orders = new ArrayList<Order>();
		for(var orderId = 1; orderId <= (Math.random() * 10); orderId++) {
			orders.add(new Order(orderId, customer.id()));
		}
		return Flux.fromIterable(orders);
	}
	
}

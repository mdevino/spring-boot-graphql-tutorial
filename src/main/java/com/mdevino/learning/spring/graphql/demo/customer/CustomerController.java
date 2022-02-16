package com.mdevino.learning.spring.graphql.demo.customer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;

import com.mdevino.learning.spring.graphql.demo.order.Order;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class CustomerController {

	@Autowired
	private CustomerRepository repository;

	@QueryMapping
	Flux<Customer> customers() {
		return this.repository.findAll();
	}

	@QueryMapping
	Flux<Customer> customersByName(@Argument String name) {
		return repository.findByName(name);
	}

	@SchemaMapping(typeName = "Customer")
	Flux<Order> orders(Customer customer) {
		var orders = new ArrayList<Order>();
		for (var orderId = 1; orderId <= (Math.random() * 10); orderId++) {
			orders.add(new Order(orderId, customer.id()));
		}
		return Flux.fromIterable(orders);
	}

	@MutationMapping
	Mono<Customer> addCustomer(@Argument String name) {
		return repository.save(new Customer(null, name));
	}

	@SubscriptionMapping
	Flux<CustomerEvent> customerEvents(@Argument Integer customerId){
		return repository.findById(customerId)
				.flatMapMany(customer -> {
					var stream = Stream.generate(
							() -> new CustomerEvent(customer, Math.random() > .5 ? CustomerEventType.DELETED : CustomerEventType.UPDATED));
					return Flux.fromStream(stream);
				})
				.delayElements(Duration.ofSeconds(1))
				.take(10);
	}

}

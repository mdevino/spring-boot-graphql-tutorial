package com.mdevino.learning.spring.graphql.demo.customer;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer>{

}

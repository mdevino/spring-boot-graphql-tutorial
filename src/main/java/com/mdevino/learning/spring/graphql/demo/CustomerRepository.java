package com.mdevino.learning.spring.graphql.demo;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface CustomerRepository extends ReactiveCrudRepository<Customer, Integer>{

}

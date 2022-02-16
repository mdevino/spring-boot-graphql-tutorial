package com.mdevino.learning.spring.graphql.demo.customer;

public record CustomerEvent(Customer customer, CustomerEventType event) {

}

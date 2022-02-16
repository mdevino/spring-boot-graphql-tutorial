package com.mdevino.learning.spring.graphql.demo.customer;

import org.springframework.data.annotation.Id;

public record Customer(@Id Integer id, String name) {

}

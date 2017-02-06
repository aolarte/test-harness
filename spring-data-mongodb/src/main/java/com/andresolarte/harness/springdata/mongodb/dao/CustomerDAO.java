package com.andresolarte.harness.springdata.mongodb.dao;

import com.andresolarte.harness.springdata.mongodb.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CustomerDAO  extends MongoRepository<Customer, String> {
    Customer findByFirstName(String firstName);
    List<Customer> findByLastName(String lastName);
}

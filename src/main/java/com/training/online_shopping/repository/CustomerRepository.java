package com.training.online_shopping.repository;


import com.training.online_shopping.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    Customer findByEmail(String email);
    Optional<Customer> findById(int id);


}

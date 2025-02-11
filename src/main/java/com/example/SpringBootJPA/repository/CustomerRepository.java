package com.example.SpringBootJPA.repository;

import com.example.SpringBootJPA.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    // Find by id
    // Find all
    Customer findByEmail(String email);
    List<Customer> findCustomerByCountry(String country);
}

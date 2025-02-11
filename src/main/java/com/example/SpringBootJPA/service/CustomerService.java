package com.example.SpringBootJPA.service;

import com.example.SpringBootJPA.model.Customer;
import com.example.SpringBootJPA.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }
    public Customer getCustomerById(long id) {
        return customerRepository.findById(id).get();
    }
    public Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
    public List<Customer>getCustomerByCountry(String country) {
        return customerRepository.findCustomerByCountry(country);

    }
}

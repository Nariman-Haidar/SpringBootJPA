package com.example.SpringBootJPA.service;

import com.example.SpringBootJPA.model.Customer;
import com.example.SpringBootJPA.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

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
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(Long id, Customer customerDetails) {
        return customerRepository.findById(id).map(customer -> {
            customer.setName(customerDetails.getName());
            customer.setEmail(customerDetails.getEmail());
            customer.setCountry(customerDetails.getCountry());
            return customerRepository.save(customer);
        }).orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
    }


    public void deleteCustomer(Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found with id: " + id);
        }
    }

}

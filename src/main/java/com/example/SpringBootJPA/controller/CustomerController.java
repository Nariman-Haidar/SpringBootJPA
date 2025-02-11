package com.example.SpringBootJPA.controller;

import com.example.SpringBootJPA.model.Customer;
import com.example.SpringBootJPA.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/customer")
    public Customer getCustomerById(@RequestParam long id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping("/customeremail")
    public Customer getCustomerByEmail(@RequestParam String email) {
        return customerService.getCustomerByEmail(email);
    }
    @GetMapping("/customercountry")
    public List<Customer> getCustomerByCountry(@RequestParam String country) {
        return customerService.getCustomerByCountry(country);
    }
}

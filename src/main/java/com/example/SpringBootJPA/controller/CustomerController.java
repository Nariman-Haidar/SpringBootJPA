package com.example.SpringBootJPA.controller;

import com.example.SpringBootJPA.model.Customer;
import com.example.SpringBootJPA.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@RequestParam long id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping("/search")
    public Customer getCustomerByEmail(@RequestParam String email) {
        return customerService.getCustomerByEmail(email);
    }
    @GetMapping("/country")
    public List<Customer> getCustomerByCountry(@RequestParam String country) {
        return customerService.getCustomerByCountry(country);
    }

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        if (customer.getName() == null || customer.getEmail() == null || customer.getCountry() == null) {
            System.out.println("Request payload: " + customer);
            return ResponseEntity.badRequest().body("Name, email, and country are required.");
        }

        Customer savedCustomer = customerService.createCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
    }





    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable long id, @RequestBody Customer customerDetails) {
        return customerService.updateCustomer(id, customerDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable long id) {
        try {
            customerService.deleteCustomer(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


}

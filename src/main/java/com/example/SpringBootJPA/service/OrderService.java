package com.example.SpringBootJPA.service;

import com.example.SpringBootJPA.model.Customer;
import com.example.SpringBootJPA.model.Order;
import com.example.SpringBootJPA.repository.CustomerRepository;
import com.example.SpringBootJPA.repository.OrderRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    public List<Order> getOrdersByCustomerId(Long customerId) {
        return orderRepository.findByCustomerId(customerId);
    }

    public Order createOrder(Long customerId, Order order) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if (customer.isPresent()) {
            order.setCustomer(customer.get());
            return orderRepository.save(order);
        } else {
            throw new RuntimeException("Customer not found with id: " + customerId);
        }
    }

    public Order updateOrder(Long orderId, Order orderDetails) {
        return orderRepository.findById(orderId).map(order -> {
            order.setProduct(orderDetails.getProduct());
            order.setQuantity(orderDetails.getQuantity());
            order.setPrice(orderDetails.getPrice());
            return orderRepository.save(order);
        }).orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
    }

    public void deleteOrder(Long orderId) {
        if (orderRepository.existsById(orderId)) {
            orderRepository.deleteById(orderId);
        } else {
            throw new RuntimeException("Order not found with id: " + orderId);
        }
    }
}


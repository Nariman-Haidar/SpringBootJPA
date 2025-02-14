package com.example.SpringBootJPA.controller;

import com.example.SpringBootJPA.model.Order;
import com.example.SpringBootJPA.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}/orders")
    public List<Order> getOrdersByCustomer(@PathVariable Long id) {
        return orderService.getOrdersByCustomerId(id);
    }

    @PostMapping("/{id}/orders")
    public Order createOrder(@PathVariable Long id, @RequestBody Order order) {
        return orderService.createOrder(id, order);
    }

    @PutMapping("/{customerId}/orders/{orderId}")
    public Order updateOrder(@PathVariable Long customerId, @PathVariable Long orderId, @RequestBody Order orderDetails) {
        return orderService.updateOrder(orderId, orderDetails);
    }

    @DeleteMapping("/orders/{orderId}")
    public void deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
    }
}

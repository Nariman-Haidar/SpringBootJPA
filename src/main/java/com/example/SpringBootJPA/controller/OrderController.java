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
}

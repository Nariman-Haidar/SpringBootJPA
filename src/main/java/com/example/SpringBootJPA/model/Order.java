package com.example.SpringBootJPA.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "orders")  // Rename the table to "orders"
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String product;
    private int quantity;
    private double price;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonBackReference  // Prevent infinite recursion
    private Customer customer;

    public Order() {}

    public Order(String product, int quantity, double price, Customer customer) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
        this.customer = customer;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getProduct() { return product; }
    public void setProduct(String product) { this.product = product; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }
}

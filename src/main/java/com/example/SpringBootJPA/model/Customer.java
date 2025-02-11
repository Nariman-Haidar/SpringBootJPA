package com.example.SpringBootJPA.model;
import jakarta.persistence.*;

import java.util.List;


@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String country;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Order> orders;


    public void setCountry(String country) {
        this.country = country;
    }
    public String getCountry() {
        return country;
    }
    public void setName(){
        this.name = name;
    }
    public String getName(){
        return name;
    }
    public void setEmail(){
        this.email = email;
    }
    public String getEmail(){
        return email;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public List<Order> getOrders() {
        return orders;
    }
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}

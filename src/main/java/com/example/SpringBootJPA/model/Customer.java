package com.example.SpringBootJPA.model;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.List;


@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) // Ensures the name is not null in the database
    private String name;

    @Column(nullable = false, unique = true) // Ensures uniqueness of email
    private String email;

    @Column(nullable = false)
    private String country;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonBackReference  // Prevent infinite recursion
    private List<Order> orders;

    public void setName(String name){
        this.name = name;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getCountry() {
        return country;
    }

    public String getName(){
        return name;
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

package com.example.PhotoMaster.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Price {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_price")
    private Long id;

    @Column(name = "name_price", nullable = false, length = 50)
    @Size(min=5, max=50)
    private String name;

    @Column(name = "price_sum", nullable = false, precision=10, scale=2)
    @NotNull
    private Double price_sum;

    @OneToMany( orphanRemoval = true, mappedBy = "price")
    private List<Orders> orders=new ArrayList<>();

    public Price(String name, Double price_sum) {
        this.name = name;
        this.price_sum = price_sum;
    }

    public Price() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice_sum() {
        return price_sum;
    }

    public void setPrice_sum(Double price_sum) {
        this.price_sum = price_sum;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }
}

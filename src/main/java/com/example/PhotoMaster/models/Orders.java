package com.example.PhotoMaster.models;

import javax.persistence.*;

@Entity
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private Long id;

    @ManyToOne(optional = true)
    private Bill bill;

    @ManyToOne(optional = true)
    private Price price;

    @Column (name = "order_count", nullable = false)
    private Integer count;

    public Orders(Bill bill, Price price, Integer count) {
        this.bill = bill;
        this.price = price;
        this.count = count;
    }

    public Orders() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}

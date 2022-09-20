package com.example.PhotoMaster.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Loyalty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_loyalty")
    private Long id;

    @OneToOne(optional = true)
    @JoinColumn(name="client_id")
    private Client client;

    @Column(name = "sum_loyalty", length = 11)
    private Integer sum;

    @ManyToOne(optional = true)
    private Status status;

    @OneToMany( orphanRemoval = true, mappedBy = "loyalty")
    private List<Bill> bills=new ArrayList<>();

    public Loyalty(Client client, Integer sum, Status status) {
        this.client = client;
        this.sum = sum;
        this.status = status;
    }

    public Loyalty() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }
}

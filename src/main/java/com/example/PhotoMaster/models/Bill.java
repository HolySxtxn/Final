package com.example.PhotoMaster.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_bill")
    private Long id;

    @Column(name = "bill_date", nullable = false)
    private String date;

    @ManyToOne(optional = true)
    private Employee employee;

    @ManyToOne(optional = true)
    private Loyalty loyalty;

    @ManyToOne(optional = true)
    private Point point;

    @OneToMany(orphanRemoval = true, mappedBy = "bill", cascade = CascadeType.ALL)
    private List<Orders> orders=new ArrayList<>();

    public Bill(String date, Employee employee, Loyalty loyalty, Point point) {
        this.date = date;
        this.employee = employee;
        this.loyalty = loyalty;
        this.point = point;
    }

    public Bill() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Loyalty getLoyalty() {
        return loyalty;
    }

    public void setLoyalty(Loyalty loyalty) {
        this.loyalty = loyalty;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

}

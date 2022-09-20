package com.example.PhotoMaster.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Status {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_status")
    private Long id;

    @Column(unique = true, nullable = false, length = 50, name = "status_name")
    @NotNull
    @Pattern(regexp = "[А-Яа-яЁё ]+")
    private String name;

    @Column(unique = true, nullable = false, name = "status_sum")
    private Integer sum;

    @Column(unique = true, nullable = false,  name = "status_discount")
    private Integer discount;

    @OneToMany( orphanRemoval = true, mappedBy = "status")
    private List<Loyalty> loyalties=new ArrayList<>();

    public Status(String name, Integer sum, Integer discount) {
        this.name = name;
        this.sum = sum;
        this.discount = discount;
    }

    public Status() {
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

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public List<Loyalty> getLoyalties() {
        return loyalties;
    }

    public void setLoyalties(List<Loyalty> loyalties) {
        this.loyalties = loyalties;
    }
}

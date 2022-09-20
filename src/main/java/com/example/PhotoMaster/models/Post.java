package com.example.PhotoMaster.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_post")
    private Long id;

    @Column(unique = true, nullable = false, length = 50, name = "post_name")
    @NotNull
    private String name;

    @Positive
    @Column(precision=38, scale=2, nullable = false, name = "salary")
    private Double salary;

    @OneToMany( orphanRemoval = true, mappedBy = "post")
    private List<Combination> combinations=new ArrayList<>();

    public Post(String name, Double salary) {
        this.name = name;
        this.salary = salary;
    }

    public Post() {
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

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public List<Combination> getCombinations() {
        return combinations;
    }

    public void setCombinations(List<Combination> combinations) {
        this.combinations = combinations;
    }
}

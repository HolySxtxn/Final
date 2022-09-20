package com.example.PhotoMaster.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Entity
public class Combination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_combination")
    private Long id;

    @ManyToOne(optional = true)
    private Employee employee;

    @ManyToOne(optional = true)
    private Post post;

    @Column(name = "part", nullable = false)
    @NotNull
    @Max(value = 1)
    private Double part;

    public Combination(Employee employee, Post post, Double part) {
        this.employee = employee;
        this.post = post;
        this.part = part;
    }

    public Combination() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Double getPart() {
        return part;
    }

    public void setPart(Double part) {
        this.part = part;
    }
}

package com.example.PhotoMaster.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_point")
    private Long id;

    @Column(nullable = false, length = 50, name = "street_point")
    @NotNull
    @Pattern(regexp = "[А-Яа-яЁё0-9 ]+")
    private String street;

    @Column(nullable = false, length = 5, name = "home_point")
    @NotNull
    private Integer home;

    @Column(nullable = true,length = 5, name = "corpus_point")
    private Integer corpus;

    @Column(nullable = true,length = 5, name = "building_point")
    private Integer building;

    @OneToMany( orphanRemoval = true, mappedBy = "point")
    private List<Bill> bills=new ArrayList<>();

    public Point(String street, Integer home, Integer corpus, Integer building) {
        this.street = street;
        this.home = home;
        this.corpus = corpus;
        this.building = building;
    }

    public Point() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getHome() {
        return home;
    }

    public void setHome(Integer home) {
        this.home = home;
    }

    public Integer getCorpus() {
        return corpus;
    }

    public void setCorpus(Integer corpus) {
        this.corpus = corpus;
    }

    public Integer getBuilding() {
        return building;
    }

    public void setBuilding(Integer building) {
        this.building = building;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }
}

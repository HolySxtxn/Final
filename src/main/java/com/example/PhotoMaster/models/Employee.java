package com.example.PhotoMaster.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employee")
    private Long id;

    @Column(nullable = false, length = 50, name = "surname_employee")
    @NotNull
    @Pattern(regexp = "[А-Яа-яЁё ]+")
    private String surname;

    @Column(nullable = false, length = 50, name = "name_employee")
    @NotNull
    @Pattern(regexp = "[А-Яа-яЁё ]+")
    private String name;

    @Column(nullable = true, length = 50, name = "middlename_employee")
    @NotNull
    @Pattern(regexp = "[А-Яа-яЁё ]+")
    private String middlename;

    @Column(nullable = false, length = 10, name = "birth_employee")
    private String birth;

    @Column(nullable = false, length = 4, name = "series_employee")
    @Pattern(regexp = "[0-9]{4}")
    private String series;

    @Column(nullable = false, length = 6, name = "number_employee")
    @Pattern(regexp = "[0-9]{6}")
    private String number;

    @Column(nullable = false, length = 12, name = "inn_employee")
    @Pattern(regexp = "[0-9]{12}")
    private String inn;

    @Column(nullable = false, length = 11, name = "snils_employee")
    @Pattern(regexp = "[0-9]{11}")
    private String snils;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany( orphanRemoval = true, mappedBy = "employee")
    private List<Combination> combinations=new ArrayList<>();

    @OneToMany( orphanRemoval = true, mappedBy = "employee")
    private List<Bill> bills=new ArrayList<>();




    public Employee() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getInn() {
        return inn;
    }

    public void setInn(String inn) {
        this.inn = inn;
    }

    public String getSnils() {
        return snils;
    }

    public void setSnils(String snils) {
        this.snils = snils;
    }

    public List<Combination> getCombinations() {
        return combinations;
    }

    public void setCombinations(List<Combination> combinations) {
        this.combinations = combinations;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public void setBills(List<Bill> bills) {
        this.bills = bills;
    }

    public Employee(String surname, String name, String middlename, String birth, String series, String number, String inn, String snils) {
        this.surname = surname;
        this.name = name;
        this.middlename = middlename;
        this.birth = birth;
        this.series = series;
        this.number = number;
        this.inn = inn;
        this.snils = snils;

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

package com.example.PhotoMaster.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client")
    private Long id;

    @Column(nullable = false, length = 50, name = "surname_client")
    @NotNull
    @Pattern(regexp = "[А-Яа-яЁё ]+")
    private String surname;

    @Column(nullable = false, length = 50, name = "name_client")
    @NotNull
    @Pattern(regexp = "[А-Яа-яЁё ]+")
    private String name;

    @Column(nullable = true, length = 50, name = "middlename_client")
    @Pattern(regexp = "[А-Яа-яЁё ]+")
    private String middlename;

    @Column(nullable = false, name = "birth_client")
    private String birth;

    @Column(nullable = false, length = 20, name = "phone_client")
    private String phone;

    @Column( unique = true, length = 50, name = "email_client")
    @Email
    private String email;
    @ManyToOne(optional = true)
    private Loyalty loyalty;

    public Client(String surname, String name, String middlename, String birth, String phone, String email) {
        this.surname = surname;
        this.name = name;
        this.middlename = middlename;
        this.birth = birth;
        this.phone = phone;
        this.email = email;
    }

    public Client() {
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Loyalty getLoyalty() {
        return loyalty;
    }

    public void setLoyalty(Loyalty loyalty) {
        this.loyalty = loyalty;
    }
}

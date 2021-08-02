package com.kcom.springstart1.webdemo.model;

import javax.persistence.*;

@Entity
@Table(name="user")

public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    @Column(name="type")
    private String type;

    @Column(name="email")
    private String email;

    @Column(name="date")
    private String date; // yyyy-MM-dd

    public User(){

    }

    public User(Integer id, String type, String email, String date) {
        this.id=id;
        this.type = type;
        this.email = email;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
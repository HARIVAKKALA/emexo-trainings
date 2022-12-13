package com.java.jdbc.entity;

public class Contact {

    private  int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    private String name;
    private String email;
    private String address;
    private String telephone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
}

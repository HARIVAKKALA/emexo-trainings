package com.java.ioc.beanfactory;

public class Student {

    private int id;
    private String name;
    private String address;

    public String getAddress() {
        return address;
    }
    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public void display(){
        System.out.println("id = "+id);
        System.out.println("name = "+name);
        System.out.print("address = "+address);
    }
}

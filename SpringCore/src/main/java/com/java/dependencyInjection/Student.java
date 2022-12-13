package com.java.dependencyInjection;

public class Student {

   private  int id;
    private String name;
    private String address;

    private Marks marks;

    public Marks getMarks() {
        return marks;
    }

    public void setMarks(Marks marks) {
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public void display(){
        System.out.println("Id = "+id);
        System.out.println("name  = "+name);
        System.out.println("address= "+address);
        marks.display();
    }
}

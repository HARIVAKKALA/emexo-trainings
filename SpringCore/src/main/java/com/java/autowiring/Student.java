package com.java.autowiring;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

public class Student {
    private  int id;
    private String name;
    private Marks marks;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Marks getMarks() {
        return marks;
    }

    public void setMarks(Marks marks) {
        this.marks = marks;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void display(){
        System.out.println("id = "+id);
        System.out.println("name = "+name);
        marks.display();
    }
}

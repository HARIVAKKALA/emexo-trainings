package com.java.autowiring;

public class Marks {
    private  int marks;

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public void display(){
        System.out.println("marks = "+marks);
    }
}

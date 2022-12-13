package com.java.autowiring;

public class Employee {
    private Student student;

    public Student getStudent() {
        return student;
    }
    public void setStudent(Student student) {
        this.student = student;
    }
    public void display(){
        System.out.println("I'm the trainer of the below student");
        student.display();
    }
}

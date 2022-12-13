package com.java.singleTone;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("employee")
public class Employee {

    private String name="hari";
    private int id=1;

    public void display(){
        System.out.println("name = "+name);
        System.out.println("Id=" +id);
    }
}

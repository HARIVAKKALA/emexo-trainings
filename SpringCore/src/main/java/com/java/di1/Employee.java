package com.java.di1;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.List;

@Component("employee")
public class Employee {

    @Value("10")
    private  int id;

    @Value(value ="{hari,giri,suri,ravi,giri}")
    private String name[];

    @Value(value = "{hari,giri,suri,ravi,giri}")
    private List<String> employees;

    public void display(){
        System.out.println("id = "+id);
        System.out.println("name = "+name[1]);
        System.out.println("list of employees  = "+employees);
    }
}

package com.java.componentScan;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.java.componentScan")
public class EmployeeTest {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.scan("com.java.componentScan");
        annotationConfigApplicationContext.refresh();

        Employee employee = annotationConfigApplicationContext.getBean("employee",Employee.class);
        System.out.println("Employee Reference = "+employee);

        Address address = annotationConfigApplicationContext.getBean("address",Address.class);
        System.out.println("Address class reference  = "+address);

    }
}

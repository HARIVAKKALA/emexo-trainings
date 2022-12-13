package com.java.di1;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class EmployeeTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.scan("com.java.di1");
        annotationConfigApplicationContext.refresh();
        Employee employee = annotationConfigApplicationContext.getBean("employee",Employee.class);
        employee.display();

    }

}

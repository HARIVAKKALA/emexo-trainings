package com.java.singleTone;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class EmployeeTest {

    public static void main(String[] args) {

        System.out.println("------first way--------------");

        AnnotationConfigApplicationContext applicationContext1 = new AnnotationConfigApplicationContext();
        applicationContext1.scan("com.java.singleTone");
        applicationContext1.refresh();

        System.out.println("------Another  way--------------");
      AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.java.singleTone");
      Employee employee =  applicationContext.getBean("employee",Employee.class);
      employee.display();

    }
}

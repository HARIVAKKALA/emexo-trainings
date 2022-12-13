package com.java.di2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestDrive {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.scan("com.java.di2");
        annotationConfigApplicationContext.refresh();

        Driver driver = annotationConfigApplicationContext.getBean("driver",Driver.class);
        driver.drive();
        annotationConfigApplicationContext.close();


    }
}

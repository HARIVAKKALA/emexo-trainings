package com.java.JavaBasedConfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Date;

public class JavaConfigTest {

    public static void main(String...args){

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext("com.java.JavaBasedConfig");
//        Date date = annotationConfigApplicationContext.getBean("date", Date.class);
//        System.out.println(date);

        Date date1 = annotationConfigApplicationContext.getBean("getDate", Date.class);
        System.out.println(date1);

        System.out.println("--------------------------------------------------------------------");
        Integer in = annotationConfigApplicationContext.getBean("getInteger", Integer.class);
        System.out.println(in);

    }

}

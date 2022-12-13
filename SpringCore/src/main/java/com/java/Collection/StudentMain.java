package com.java.Collection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StudentMain {

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("collection.xml") ;
        College college = context.getBean("college", College.class);
        college.getCollegeDetails();
    }
}

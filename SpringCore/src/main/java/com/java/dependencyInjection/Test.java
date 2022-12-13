package com.java.dependencyInjection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("Di.xml");
        Student student = applicationContext.getBean("stu", Student.class);
        student.display();
    }
}

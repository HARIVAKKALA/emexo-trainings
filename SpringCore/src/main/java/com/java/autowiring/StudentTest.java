package com.java.autowiring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StudentTest {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("autowiring.xml");
        Student student = applicationContext.getBean("student", Student.class);
        student.display();

        Employee employee = applicationContext.getBean("employee",Employee.class);
        //System.out.println("Employee reference = "+employee);
        //System.out.println("Student reference = "+student);
        employee.display();
    }
}

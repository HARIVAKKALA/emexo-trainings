package com.java.io.beanfactory;

import com.java.io.beanfactory.Employee;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EmployeeTest {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext classPathXmlApplicationContext= new ClassPathXmlApplicationContext("bean.xml");
        Employee emp = classPathXmlApplicationContext.getBean("emp",Employee.class);
        System.out.println("here we calling display method");
        emp.display();


    }
}

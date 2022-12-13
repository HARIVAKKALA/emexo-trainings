package com.java.ioc.beanfactory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.logging.XMLFormatter;

public class StudentTest {

    public static void main(String[] args) {

        System.out.println("----------------By using BeanFactory-------------");
        Resource resource = new ClassPathResource("studentBean.xml");
        BeanFactory beanFactory = new XmlBeanFactory(resource);
        Student student1 = (Student)beanFactory.getBean("stu1");
        student1.display();
        System.out.println();

        System.out.println();
        System.out.println("------By Using FileSystem----------");
        BeanFactory beanFactory1 = new FileSystemXmlApplicationContext("C:\\Users\\vakka\\Documents\\SpringCore\\src\\main\\resources\\studentBean.xml");
        Student student2 = beanFactory1.getBean("stu",Student.class);
        student2.display();
        System.out.println();

        System.out.println("-------By Using Application Context----------------");
        ApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("studentBean.xml");
        Student student =  classPathXmlApplicationContext.getBean("stu",Student.class);
        student.display();
        System.out.println();

        System.out.println("------******By Using FileSystem********----------");
        ApplicationContext applicationContext  = new FileSystemXmlApplicationContext("C:\\Users\\vakka\\Documents\\SpringCore\\src\\main\\resources\\studentBean.xml");
        Student student3 = beanFactory1.getBean("stu",Student.class);
        student3.display();
        System.out.println();
    }
}

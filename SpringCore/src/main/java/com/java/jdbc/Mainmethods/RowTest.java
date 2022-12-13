package com.java.jdbc.Mainmethods;

import com.java.jdbc.DAO.ContactDAO;
import com.java.jdbc.entity.Contact;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class RowTest {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext("com.java.jdbc");/*it will scan the list of classes  present inside the package and sub packages creates the bean stored inside the container*/
        ContactDAO contactDAO = annotationConfigApplicationContext.getBean("contactDao",ContactDAO.class);
        List<Contact> contactList = contactDAO.getListofObjects();

        /*iterating list of objects by using for each loop*/
        for (Contact contact: contactList) {
            System.out.println("-------------------------------------------");
            System.out.println("Id = "+contact.getId());
            System.out.println("Name = "+contact.getName());
            System.out.println("Email = "+contact.getEmail());
            System.out.println("Address"+contact.getAddress());
            System.out.println("Telephone number ="+contact.getTelephone());
        }
    }
}

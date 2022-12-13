package com.java.jdbc.Mainmethods;

import com.java.jdbc.DAO.ContactDAO;
import com.java.jdbc.entity.Contact;
import com.java.jdbc.service.ContactService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class ServiceTest {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext("com.java.jdbc");/*it will scan the list of classes  present inside the package and sub packages creates the bean stored inside the container*/

        ContactService contactService = annotationConfigApplicationContext.getBean("contactService", ContactService.class);
        Contact contact = new Contact();
        contact.setName("vakkala hari");
        contact.setEmail("hari@gmail.com");
        contact.setAddress("bangalore");
        contact.setTelephone("899899");
        contactService.save(contact);

    }
}
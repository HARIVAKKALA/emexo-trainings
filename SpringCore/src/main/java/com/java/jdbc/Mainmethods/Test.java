package com.java.jdbc.Mainmethods;

import com.java.jdbc.DAO.ContactDAO;
import com.java.jdbc.entity.Contact;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException {

        AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext("com.java.jdbc");
//        annotationConfigApplicationContext.scan("com.java.jdbc");
        //annotationConfigApplicationContext.refresh();
        ContactDAO contactDAO = annotationConfigApplicationContext.getBean("contactDao",ContactDAO.class);

        /*insert query*/
        Contact contact = new Contact();
        contact.setName("siri");
        contact.setAddress("apple");
        contact.setEmail("siri@apple.com");
        contact.setTelephone("121212121");
        contactDAO.save(contact);

       /*updating email*/
        Contact contact1 = new Contact();
        contact1.setEmail("vakkala@gmail.com");
        contact1.setId(6);
        contactDAO.updateEmial(contact1);

        /*updating contact number*/
        Contact contact2 =new Contact();
        contact2.setTelephone("8978646818");
        contact2.setId(4);
        contactDAO.updateContact(contact2);

        /*Deleting a single row based on id*/
        Contact contact3 = new Contact();
        contact3.setId(3);
        contactDAO.deleteRecord(contact3);
    }

}

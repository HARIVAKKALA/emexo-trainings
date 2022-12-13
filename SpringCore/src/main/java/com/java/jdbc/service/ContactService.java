package com.java.jdbc.service;

import com.java.jdbc.DAO.ContactDAO;
import com.java.jdbc.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("contactService")
public class ContactService {
    @Autowired
    ContactDAO contactDAO;
    @Transactional
    public int save(Contact contact) {
        return  contactDAO.save(contact);
    }
}

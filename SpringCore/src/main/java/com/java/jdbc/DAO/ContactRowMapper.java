package com.java.jdbc.DAO;

import com.java.jdbc.entity.Contact;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactRowMapper implements RowMapper<Contact> {

    @Override
    public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
        Contact contact = new Contact();
        contact.setId(rs.getInt("id"));
        contact.setName(rs.getString("name"));
        contact.setEmail(rs.getString("email"));
        contact.setAddress(rs.getString("address"));
        contact.setTelephone(rs.getString("telephone"));
        return contact;
    }
}

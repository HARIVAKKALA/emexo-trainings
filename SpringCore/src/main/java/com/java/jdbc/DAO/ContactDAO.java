package com.java.jdbc.DAO;
import com.java.jdbc.entity.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("contactDao")
public class ContactDAO {
    @Autowired /*injecting object*/
    public JdbcTemplate jdbcTemplate;

    public int save(Contact contact) {

        //System.out.println("the JDBC Template object = "+jdbcTemplate);
        String sql = "INSERT INTO contact (name, email, address, telephone) VALUES (?, ?, ?, ?)";
        int response = jdbcTemplate.update(sql, contact.getName(), contact.getEmail(), contact.getAddress(), contact.getTelephone());
        return response;
    }

    public int updateEmial(Contact contact){
        String sql = "Update contact set email = ? where id = ?";
         int response = jdbcTemplate.update(sql,contact.getEmail(),contact.getId());
         return response;
    }

    public int updateContact(Contact contact){

            String sql = "Update contact set telephone = ? where id = ? ";
            int response = jdbcTemplate.update(sql, contact.getTelephone(), contact.getId());
            return response;
    }
    /*deleting a row based on some id*/
    public  int deleteRecord(Contact contact){

        String sql = "Delete from contact where id = ?";
        int response =  jdbcTemplate.update(sql,contact.getId());
        return  response;
    }
    /*retriving list of objects*/
    public List<Contact> getListofObjects(){
        String sql ="select *From contact";
       return jdbcTemplate.query(sql, new ContactRowMapper());
    }

//    public Contact getSingleRecord(){
//
//        String sql= "select *from contact where id=?";
//        jdbcTemplate.queryForObject(sql,id);
//
//    }
}


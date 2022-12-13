package com.java.SpringAop.Test;

import com.java.SpringAop.DAO.PassengerDAO;
import com.java.SpringAop.DAO.PassengerDAOImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class PassengerTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("com.java.SpringAop");
        PassengerDAO passengerDao = context.getBean("passengerDaoImpl", PassengerDAO.class);
        //System.out.println(passengerDao.getPassenger(1));
        passengerDao.getPassenger(2);
        passengerDao.getPassenger(3);
        //context.close();
    }
}

package com.java.di2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.awt.desktop.SystemSleepEvent;

@Component
public class Driver {

    @Autowired
    @Qualifier("benz")
    Vehicle vehicle;

    public Driver(){
        System.out.println("Driver class executed");
    }
    public void drive(){
        vehicle.riding();
    }
}

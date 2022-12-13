package com.java.di2;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class PassionPro implements Vehicle {
    @Override
    public void riding() {

        System.out.println("hey i'm riding PassionPro");
    }
}

package com.java.di2;

import org.springframework.stereotype.Component;

@Component
public class RoyalEnfield implements Vehicle{

    @Override
    public void riding() {
        System.out.println("hey i'm riding bullet");
    }
}

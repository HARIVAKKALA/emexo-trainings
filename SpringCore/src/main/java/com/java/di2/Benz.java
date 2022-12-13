package com.java.di2;

import org.springframework.stereotype.Component;

@Component
public class Benz implements Vehicle{
    @Override
    public void riding() {
        System.out.println("hey finally i got Benz");
    }
}

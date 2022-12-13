package com.java.JavaBasedConfig;

import jdk.jfr.Description;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.util.Date;

@Configuration
public class JavaBasedConfig {

    @Description("Date Bean")
    @Scope("prototype")
    @Bean(name= "date")
    //@Bean by defalut the method name is the bean id, if we don't specify any bean name
    public Date getDate(){
        Date date = new Date();
        return date;
    }

    @Description("This is Integer class of Bean")
    @Scope /*by default it is single tone*/
    @Bean /*by default method name is the bean id */
    public Integer getInteger(){
        Integer in = new Integer(12);
        return in;
    }
}

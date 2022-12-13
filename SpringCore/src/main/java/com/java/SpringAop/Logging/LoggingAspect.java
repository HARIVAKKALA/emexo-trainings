package com.java.SpringAop.Logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LogManager.getLogger(LoggingAspect.class.getName());

    @Before("execution(* com.java.SpringAop.DAO.PassengerDAOImpl.getPassenger(..))")
    public void before(JoinPoint joinPoint){
        Object[] arguments = joinPoint.getArgs();
        logger.info("Input to Method :" + joinPoint.getSignature().getName() + " with args :" + arguments[0]);
    }

  @After("execution(* com.java.SpringAop.DAO.PassengerDAOImpl )")
  public void after( JoinPoint joinPoint){
        Object[]arguments = joinPoint.getArgs();
        logger.info("Input to Method :"+joinPoint.getSignature().getName()+"with args :" +arguments [0]);
  }

}

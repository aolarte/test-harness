package com.andresolarte.harness.spring4.aspectj.interceptor;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TestAdvice {

    @Before("execution(* com.andresolarte.harness.spring4.aspectj.service.MyService.*(..))")
    public void doBefore() throws Throwable {
        System.out.println("Executing 'before' logic");
    }


    @Pointcut("execution(* com.andresolarte.harness.spring4.aspectj.service.MyService.*(..)) && " +
            "!execution(* com.andresolarte.harness.spring4.aspectj.service.MyService.get*(..))")
    private void nonGetOperations() {}

    @After("nonGetOperations()")
    public void doAfter() {
        System.out.println("Executing 'after' logic");
    }
}

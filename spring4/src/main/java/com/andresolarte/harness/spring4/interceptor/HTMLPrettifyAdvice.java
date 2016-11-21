package com.andresolarte.harness.spring4.interceptor;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class HTMLPrettifyAdvice {

    @Around("@annotation(htmlPrettify)")
    public Object prettify(ProceedingJoinPoint pjp, HTMLPrettify htmlPrettify) throws Throwable {
        return "<p>" + pjp.proceed() + "</p>";
    }
}

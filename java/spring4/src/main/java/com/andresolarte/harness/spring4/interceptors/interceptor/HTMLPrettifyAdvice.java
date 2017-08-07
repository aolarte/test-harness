package com.andresolarte.harness.spring4.interceptors.interceptor;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class HTMLPrettifyAdvice {

    @Around("@annotation(htmlPrettify)")
    public Object prettify(ProceedingJoinPoint pjp, HTMLPrettify htmlPrettify) throws Throwable {
        return "<p class='"+htmlPrettify.value()+"'>" + pjp.proceed() + "</p>";
    }


//    @Around("@within(com.andresolarte.harness.spring4.interceptors.interceptor.HTMLPrettify)" +
//            "|| @annotation(com.andresolarte.harness.spring4.interceptors.interceptor.HTMLPrettify)")
//    public Object prettify2(ProceedingJoinPoint pjp) throws Throwable {
//        return "<p>" + pjp.proceed() + "</p>";
//    }


//    @Around("@within(htmlPrettify)" +
//            "&& !@annotation(com.andresolarte.harness.spring4.interceptors.interceptor.HTMLPrettify)")
//    public Object prettify3(ProceedingJoinPoint pjp, HTMLPrettify htmlPrettify) throws Throwable {
//        return "<p>" + pjp.proceed() + "</p>";
//    }

}

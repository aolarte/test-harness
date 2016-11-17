package com.andresolarte.harness.guice.interceptor;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class HTMLPrettifyInterceptor implements MethodInterceptor {

    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object result = invocation.proceed();
        if (invocation.getMethod().getReturnType() == String.class) {
            System.out.println("Prettifying Output call to method: "
                    + invocation.getMethod().getName());
            result = "<p>" + result + "</p>";
        }
        return result;
    }
}

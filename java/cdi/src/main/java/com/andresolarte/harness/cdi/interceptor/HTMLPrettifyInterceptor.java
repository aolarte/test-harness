package com.andresolarte.harness.cdi.interceptor;




import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;


@Interceptor
@HTMLPrettify
public class HTMLPrettifyInterceptor {

    @AroundInvoke
    public Object prettifyOutput(InvocationContext invocationContext)
            throws Exception {
        Object result = invocationContext.proceed();
        if (invocationContext.getMethod().getReturnType() == String.class) {
            System.out.println("Prettifying Output call to method: "
                    + invocationContext.getMethod().getName());
            result = "<p>" + result + "</p>";
        }
        return result;
    }
}

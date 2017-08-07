package com.andresolarte.harness.spring4.interceptors;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@Configuration
@ComponentScan(basePackageClasses = Spring4InterceptorTest.class)
@EnableAspectJAutoProxy(proxyTargetClass = true) //This will enable/disable interceptors
public class Spring4InterceptorTest {


    public static void main(String... args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(Spring4InterceptorTest.class);

        InterceptorApp app = context.getBean(InterceptorApp.class);
        app.run();
    }
}

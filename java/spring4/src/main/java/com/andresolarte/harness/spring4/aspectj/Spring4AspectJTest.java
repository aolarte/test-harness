package com.andresolarte.harness.spring4.aspectj;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@Configuration
@ComponentScan(basePackageClasses = Spring4AspectJTest.class)
@EnableAspectJAutoProxy(proxyTargetClass = true) //This will enable/disable interceptors
public class Spring4AspectJTest {


    public static void main(String... args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(Spring4AspectJTest.class);

        AspectJApp app = context.getBean(AspectJApp.class);
        app.run();
    }
}

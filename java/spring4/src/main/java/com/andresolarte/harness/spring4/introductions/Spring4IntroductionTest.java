package com.andresolarte.harness.spring4.introductions;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@Configuration
@ComponentScan(basePackageClasses = Spring4IntroductionTest.class)
@EnableAspectJAutoProxy(proxyTargetClass = true) //This will enable/disable interceptors
public class Spring4IntroductionTest {


    public static void main(String... args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(Spring4IntroductionTest.class);

        IntroductionApp app = context.getBean(IntroductionApp.class);
        app.run();
    }
}

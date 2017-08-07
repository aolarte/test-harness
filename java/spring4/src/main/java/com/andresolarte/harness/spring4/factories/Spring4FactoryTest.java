package com.andresolarte.harness.spring4.factories;

import com.andresolarte.harness.spring4.factories.service.ITestService;
import com.andresolarte.harness.spring4.factories.service.OldTestService;
import com.andresolarte.harness.spring4.factories.service.TestService;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;


@Configuration
@ComponentScan(basePackageClasses = Spring4FactoryTest.class)
public class Spring4FactoryTest {


    @Bean
    List<ObjectFactory<ITestService>> testServiceFactoryBeans(ObjectFactory<TestService> testServiceObjectFactory,
                                                              ObjectFactory<OldTestService> oldTestServiceObjectFactory) {
        List ret = Arrays.asList(testServiceObjectFactory, oldTestServiceObjectFactory);
        return (List<ObjectFactory<ITestService>>) ret;
    }

    public static void main(String... args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(Spring4FactoryTest.class);

        FactoryApp app = context.getBean(FactoryApp.class);
        app.run();
    }
}

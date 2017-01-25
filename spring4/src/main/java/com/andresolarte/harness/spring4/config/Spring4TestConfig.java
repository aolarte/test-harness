package com.andresolarte.harness.spring4.config;


import com.andresolarte.harness.spring4.service.ITestService;
import com.andresolarte.harness.spring4.service.OldTestService;
import com.andresolarte.harness.spring4.service.TestService;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.Arrays;
import java.util.List;

@Configuration
@ComponentScan("com.andresolarte.harness.spring4")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class Spring4TestConfig {


    @Bean
    List<ObjectFactory<ITestService>> testServiceFactoryBeans(ObjectFactory<TestService> testServiceObjectFactory,
                                                              ObjectFactory<OldTestService> oldTestServiceObjectFactory) {
        List ret = Arrays.asList(testServiceObjectFactory, oldTestServiceObjectFactory);
        return (List<ObjectFactory<ITestService>>) ret;
    }
}

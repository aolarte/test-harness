package com.andresolarte.harness.spring4.properties;

import com.andresolarte.harness.spring4.interceptors.InterceptorApp;
import com.andresolarte.harness.spring4.properties.source.ConstantPropertySource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;


@Configuration
@ComponentScan(basePackageClasses = Spring4PropertiesTest.class)
@PropertySource("/props/services.properties")
public class Spring4PropertiesTest {



    public static void main(String... args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(Spring4PropertiesTest.class);

        PropertiesApp app = context.getBean(PropertiesApp.class);
        app.run();
    }
}

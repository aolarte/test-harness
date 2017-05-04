package com.andresolarte.harness.spring4.properties;

import com.andresolarte.harness.spring4.properties.source.ConstantPropertySource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.ConfigurableEnvironment;

import javax.annotation.PostConstruct;


@Configuration
@ComponentScan(basePackageClasses = Spring4PropertiesTest.class)
@PropertySource("/props/services.properties")
public class Spring4PropertiesTest {

    @Autowired
    private ConfigurableEnvironment env;

    @PostConstruct
    public void init() {
        env.getPropertySources().addFirst(new ConstantPropertySource());
    }

    public static void main(String... args) throws Exception {
        ApplicationContext context = new AnnotationConfigApplicationContext(Spring4PropertiesTest.class);
        PropertiesApp app = context.getBean(PropertiesApp.class);
        app.run();
    }
}

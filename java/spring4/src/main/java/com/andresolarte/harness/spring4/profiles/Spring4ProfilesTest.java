package com.andresolarte.harness.spring4.profiles;

import com.andresolarte.harness.spring4.qualifiers.QualifierApp;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.AbstractEnvironment;


@Configuration
@ComponentScan(basePackageClasses = Spring4ProfilesTest.class)
public class Spring4ProfilesTest {


    public static void main(String... args) throws Exception {
        System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "dev");
        ApplicationContext context = new AnnotationConfigApplicationContext(Spring4ProfilesTest.class);

        ProfileApp app = context.getBean(ProfileApp.class);
        app.run();
    }

}

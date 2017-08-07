package com.andresolarte.harness.cloudstream;


import com.andresolarte.harness.cloudstream.kafka.EmbeddedKafka;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableAutoConfiguration
@ComponentScan("com.andresolarte.harness.cloudstream.config")
public class CloudStreamApp {
    public static void main(String[] args) {

        SpringApplication.run(CloudStreamApp.class, args);
    }
}

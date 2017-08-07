package com.andresolarte.harness.eureka.embedded;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EmbeddedEurekaTestServer {
    public static void main(String[] args) {
        SpringApplication.run(EmbeddedEurekaTestServer.class, args);
    }
}

package com.andresolarte.harness.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaTestServer {
    public static void main(String[] args) {
        SpringApplication.run(EurekaTestServer.class, args);
    }
}

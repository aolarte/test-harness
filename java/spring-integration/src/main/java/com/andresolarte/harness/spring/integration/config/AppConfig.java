package com.andresolarte.harness.spring.integration.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.file.FileNameGenerator;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.messaging.Message;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@EnableIntegration
@Configuration
@IntegrationComponentScan("com.andresolarte.harness.spring.integration.service")
public class AppConfig {

    @Bean
    public IntegrationFlow readFromFile() throws IOException {
        File file = Files.createTempDirectory("sprint_int_test").toFile();
        FileWritingMessageHandler handler = new FileWritingMessageHandler(file);
        handler.setFileNameGenerator(m -> "test.tst");
        return IntegrationFlows.from("gateway")
                .handle(handler)
                .get();

    }

}

package com.andresolarte.harness.spring4.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan("com.andresolarte.harness.spring4")
@EnableAspectJAutoProxy
public class Spring4TestConfig {
}

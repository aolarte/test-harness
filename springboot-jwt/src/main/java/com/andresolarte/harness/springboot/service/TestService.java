package com.andresolarte.harness.springboot.service;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Component
public class TestService implements ITestService {

    public String run() {
        RestTemplate restTemplate = new RestTemplate();

       // Map<Long,String> m = restTemplate.getForObject("http://localhost:8080/test", Map<Long,String>.class);

        ParameterizedTypeReference<Map<Long, List<Map>>> typeRef = new ParameterizedTypeReference<Map<Long, List<Map>>>() {
        };

        ResponseEntity<Map<Long, List<Map>>> response = restTemplate.exchange("http://localhost:8080/test",
                HttpMethod.GET, null, typeRef);
        Map<Long,List<Map>> m = response.getBody();

        String s = restTemplate.getForObject("http://localhost:8080/test", String.class);
        return s;
    }

    public String buildMessage() {

        return "Hello World!";
    }
}

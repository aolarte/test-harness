package com.andresolarte.harness.jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class JacksonEnumTest {

    public static void main(String... args) throws IOException {
        TestObject testObject = new TestObject();
        testObject.setName("Name #1");
        testObject.setValue(TestEnum.DAILY);

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(testObject));

        TestObject testObject2 = objectMapper.readValue("{\"name\":\"Name #1\",\"value\":\"DAILY\"}", TestObject.class);
        System.out.println(testObject2.getValue());
    }

}

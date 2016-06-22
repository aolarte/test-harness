package com.andresolarte.harness.eureka.client;

import com.andresolarte.harness.eureka.service.IEndpoint;
import feign.Feign;
import feign.codec.Decoder;
import feign.jackson.JacksonDecoder;
import feign.Logger;
import feign.jaxrs.JAXRSContract;

public class FeignClient {

    public static void main(String... args) {
        new FeignClient().run();
    }

    public void run() {
        IEndpoint endpoint=createClient();
        System.out.println("Result: "+endpoint.message().message);
    }

    public IEndpoint createClient() {
        Decoder decoder = new JacksonDecoder();
        return Feign.builder()
                .decoder(decoder)
                //.errorDecoder(new ErrorDecoder(decoder))
                .contract(new JAXRSContract())
                .logger(new Logger.ErrorLogger())
                .logLevel(Logger.Level.BASIC)
                .target(IEndpoint.class, "http://localhost:9761");
    }
}

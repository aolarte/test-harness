package com.andresolarte.harness.spring.integration.service;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.Message;

@MessagingGateway
public interface IGateway {

    @Gateway(requestChannel="gateway")
    Message sendMessage(String message);

}

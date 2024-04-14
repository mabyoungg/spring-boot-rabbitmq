package org.example.springbootrabbitmq.global.stomp;

public interface StompMessageTemplate {
    void convertAndSend(String type, String destination, Object payload);
}

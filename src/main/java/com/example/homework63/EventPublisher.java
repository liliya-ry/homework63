package com.example.homework63;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishStringEvent(final String message) {
        System.out.println("Publishing custom String event with message " + message);
        StringEvent customEvent = new StringEvent(this, message);
        applicationEventPublisher.publishEvent(customEvent);
    }

    public void publishBooleanEvent(final boolean flag) {
        System.out.println("Publishing custom boolean event with value " + flag);
        BooleanEvent customEvent = new BooleanEvent(this, flag);
        applicationEventPublisher.publishEvent(customEvent);
    }
}

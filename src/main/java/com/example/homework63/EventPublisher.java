package com.example.homework63;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.*;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class EventPublisher {
    Set<ApplicationEvent> events = new HashSet<>();
    List<Long> publishTimes = new ArrayList<>();

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publishStringEvent(final String message) {
        publishTimes.add(System.nanoTime());
        System.out.println("Publishing custom String event with message " + message);
        StringEvent customEvent = new StringEvent(this, message);
        events.add(customEvent);
        applicationEventPublisher.publishEvent(customEvent);
    }

    public void publishBooleanEvent(final boolean flag) {
        publishTimes.add(System.nanoTime());
        System.out.println("Publishing custom boolean event with value " + flag);
        BooleanEvent customEvent = new BooleanEvent(this, flag);
        events.add(customEvent);
        applicationEventPublisher.publishEvent(customEvent);
    }
}

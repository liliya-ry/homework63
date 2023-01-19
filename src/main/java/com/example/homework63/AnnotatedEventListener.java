package com.example.homework63;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import java.util.*;

public class AnnotatedEventListener {
    List<ApplicationEvent> events = new ArrayList<>();

    @EventListener
    public void handleStringEvent(StringEvent event) {
        events.add(event);
        System.out.println("Received custom String event - " + event.getMessage());
    }

    @EventListener
    public void handleBooleanEvent(BooleanEvent event) {
        events.add(event);
        System.out.println("Received custom boolean event - " + event.getFlag());
    }
}

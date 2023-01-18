package com.example.homework63;

import org.springframework.context.event.EventListener;

public class AnnotatedEventListener {
    @EventListener
    public void handleStringEvent(StringEvent event) {
        System.out.println("Received custom String event - " + event.getMessage());
    }

    @EventListener
    public void handleBooleanEvent(BooleanEvent event) {
        System.out.println("Received custom boolean event - " + event.getFlag());
    }
}

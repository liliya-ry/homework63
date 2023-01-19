package com.example.homework63;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class BooleanEventListener implements ApplicationListener<BooleanEvent> {
    BooleanEvent event;

    @Override
    public void onApplicationEvent(BooleanEvent event) {
        this.event = event;
        System.out.println("Received custom boolean event - " + event.getFlag());
    }
}
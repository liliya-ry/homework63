package com.example.homework63;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StringEventListener implements ApplicationListener<StringEvent> {
    @Override
    public void onApplicationEvent(StringEvent event) {
        System.out.println("Received custom String event - " + event.getMessage());
    }
}

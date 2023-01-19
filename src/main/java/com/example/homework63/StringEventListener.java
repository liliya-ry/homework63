package com.example.homework63;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StringEventListener implements ApplicationListener<StringEvent> {
    StringEvent event;
    List<Long> receiveTimes = new ArrayList<>();


    @Override
    public void onApplicationEvent(StringEvent event) {
        receiveTimes.add(System.nanoTime());
        this.event = event;
        System.out.println("Received custom String event - " + event.getMessage());
    }
}

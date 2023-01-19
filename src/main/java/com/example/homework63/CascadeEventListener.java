package com.example.homework63;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;

public class CascadeEventListener {
    ApplicationEvent event;

    @EventListener
    public StringEvent handleStringEvent(StringEvent event) {
        this.event = event;
        String msg = event.getMessage();
        System.out.println("Cascade listener received event with message - " + msg);

        if (msg == null) {
            return null;
        }

        return event.getMessage().equals("hello") ?
                new StringEvent(event, msg + " world") :
                null;
    }
}

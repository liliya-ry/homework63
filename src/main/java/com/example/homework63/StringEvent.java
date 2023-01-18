package com.example.homework63;

import org.springframework.context.ApplicationEvent;

public class StringEvent extends ApplicationEvent {
    private final String message;

    public StringEvent(Object source, String message) {
        super(source);
        this.message = message;
    }
    public String getMessage() {
        return message;
    }
}

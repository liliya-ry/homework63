package com.example.homework63;

import org.springframework.context.ApplicationEvent;

public class BooleanEvent extends ApplicationEvent {
    private final boolean flag;

    public BooleanEvent(Object source, boolean flag) {
        super(source);
        this.flag = flag;
    }
    public boolean getFlag() {
        return flag;
    }
}

package com.example.homework63;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.*;
import org.springframework.context.event.EventListener;

import java.util.*;


public class StandardEventsListener {
    Set<Class<?>> events = new HashSet<>();

    @EventListener
    public void handleContextStart(ContextStartedEvent cse) {
        events.add(cse.getClass());
        System.out.println("Handling context started event.");
    }

    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent cse) {
        events.add(cse.getClass());
        System.out.println("Handling context refreshed event.");
    }

    @EventListener
    public void handleContextClosed(ContextClosedEvent cse) {
        events.add(cse.getClass());
        System.out.println("Handling context closed event.");
    }

    @EventListener
    public void handleContextStopped(ContextStoppedEvent cse) {
        events.add(cse.getClass());
        System.out.println("Handling context stopped event.");
    }
}

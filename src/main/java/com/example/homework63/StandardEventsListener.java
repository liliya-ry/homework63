package com.example.homework63;

import org.springframework.context.event.*;

public class StandardEventsListener {
    @EventListener
    public void handleContextStart(ContextStartedEvent cse) {
        System.out.println("Handling context started event.");
    }

    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent cse) {
        System.out.println("Handling context refreshed event.");
    }

    @EventListener
    public void handleContextClosed(ContextClosedEvent cse) {
        System.out.println("Handling context closed event.");
    }

    @EventListener
    public void handleContextStopped(ContextStoppedEvent cse) {
        System.out.println("Handling context stopped event.");
    }
}

package com.example.homework63;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

class EventsTest {
    @Test
    @DisplayName("Publish and receive events using listener interfaces")
    void test1() {
        ApplicationContext context = new AnnotationConfigApplicationContext(
                EventPublisher.class,
                BooleanEventListener.class,
                StringEventListener.class);

        EventPublisher publisher = context.getBean(EventPublisher.class);
        assertNotNull(publisher);
        publish(publisher);
    }

    @Test
    @DisplayName("Publish and receive events using annotated listener")
    void test2() {
        ApplicationContext context = new AnnotationConfigApplicationContext(EventPublisher.class, AnnotatedEventListener.class);
        EventPublisher publisher = context.getBean(EventPublisher.class);
        assertNotNull(publisher);
        publish(publisher);
    }

    void publish(EventPublisher publisher) {
        publisher.publishStringEvent("some message 1");
        publisher.publishStringEvent("some message 2");
        publisher.publishStringEvent("some message 3");
        publisher.publishBooleanEvent(true);
        publisher.publishBooleanEvent(false);
    }

    @Test
    void asyncEvents() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AsyncConfig.class);
        EventPublisher publisher = context.getBean(EventPublisher.class);
        assertNotNull(publisher);
        publish(publisher);
    }

    @Test
    void standardEvents() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(StandardEventsListener.class);
        context.stop();
        context.start();
        context.close();
    }

    @Test
    void cascadeEvents() {
        ApplicationContext context = new AnnotationConfigApplicationContext(EventPublisher.class, CascadeEventListener.class);
        EventPublisher publisher = context.getBean(EventPublisher.class);
        assertNotNull(publisher);
        publisher.publishStringEvent(null);
        publisher.publishStringEvent("some message");
        publisher.publishStringEvent("hello");
    }
}

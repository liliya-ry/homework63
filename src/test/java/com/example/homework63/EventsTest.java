package com.example.homework63;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.springframework.context.*;
import org.springframework.context.annotation.*;
import org.springframework.context.event.*;

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

        StringEventListener strListener = context.getBean(StringEventListener.class);
        assertNotNull(strListener);
        assertTrue(publisher.events.contains(strListener.event));

        BooleanEventListener bListener = context.getBean(BooleanEventListener.class);
        assertNotNull(bListener);
        assertTrue(publisher.events.contains(bListener.event));
    }

    @Test
    @DisplayName("Publish and receive events using annotated listener")
    void test2() {
        ApplicationContext context = new AnnotationConfigApplicationContext(EventPublisher.class, AnnotatedEventListener.class);

        EventPublisher publisher = context.getBean(EventPublisher.class);
        assertNotNull(publisher);
        publish(publisher);

        AnnotatedEventListener listener = context.getBean(AnnotatedEventListener.class);
        assertNotNull(listener);
        for (ApplicationEvent event : publisher.events)
            assertTrue(publisher.events.contains(event));
    }

    void publish(EventPublisher publisher) {
        publishStringEvents(publisher);
        publisher.publishBooleanEvent(true);
        publisher.publishBooleanEvent(false);
    }

    void publishStringEvents(EventPublisher publisher) {
        publisher.publishStringEvent("some message 1");
        publisher.publishStringEvent("some message 2");
        publisher.publishStringEvent("some message 3");
    }

    @Test
    void asyncEvents() throws InterruptedException {
        ApplicationContext context = new AnnotationConfigApplicationContext(AsyncConfig.class);
        EventPublisher publisher = context.getBean(EventPublisher.class);
        assertNotNull(publisher);
        publishStringEvents(publisher);

        StringEventListener listener = context.getBean(StringEventListener.class);
        assertNotNull(listener);

        for (int i = 1; i < publisher.publishTimes.size(); i++) {
            long curPublishTime = publisher.publishTimes.get(i);
            long prevReceiveTime = listener.receiveTimes.get(i);
            assertTrue(curPublishTime < prevReceiveTime);
        }
    }

    @Test
    void standardEvents() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(StandardEventsListener.class);
        StandardEventsListener listener = context.getBean(StandardEventsListener.class);
        assertNotNull(listener);
        assertTrue(listener.events.contains(ContextRefreshedEvent.class));

        context.stop();
        assertTrue(listener.events.contains(ContextStoppedEvent.class));

        context.start();
        assertTrue(listener.events.contains(ContextStartedEvent.class));

        context.close();
        assertTrue(listener.events.contains(ContextClosedEvent.class));
    }

    @Test
    void cascadeEvents() {
        ApplicationContext context = new AnnotationConfigApplicationContext(EventPublisher.class, CascadeEventListener.class);
        EventPublisher publisher = context.getBean(EventPublisher.class);
        assertNotNull(publisher);

        CascadeEventListener listener = context.getBean(CascadeEventListener.class);
        assertNotNull(listener);

        publisher.publishStringEvent(null);
        assertTrue(publisher.events.contains(listener.event));

        publisher.publishStringEvent("some message");
        assertTrue(publisher.events.contains(listener.event));

        publisher.publishStringEvent("hello");
        assertFalse(publisher.events.contains(listener.event));
    }
}

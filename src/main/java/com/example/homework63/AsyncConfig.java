package com.example.homework63;

import org.springframework.context.annotation.*;
import org.springframework.context.event.*;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@ComponentScan
@Configuration
public class AsyncConfig {
    @Bean(name = "applicationEventMulticaster")
    public ApplicationEventMulticaster simpleApplicationEventMulticaster() {
        SimpleApplicationEventMulticaster eventMulticaster =
                new SimpleApplicationEventMulticaster();

        eventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
        return eventMulticaster;
    }
}

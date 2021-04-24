package ru.vdsimako.taskmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.vdsimako.taskmanagement.model.entity.Task;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Configuration
public class TaskManagementConfiguration {

    @Bean
    public Map<Long, Task> taskMap() {
        return Collections.synchronizedMap(new HashMap<>());
    }

    @Bean
    public AtomicLong taskSequence() {
        return new AtomicLong(0L);
    }
}

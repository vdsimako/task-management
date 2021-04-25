package ru.vdsimako.taskmanagement.provider;

import ru.vdsimako.taskmanagement.model.dto.TaskDto;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

public class TaskDtoProvider {
    public static TaskDto getTaskDto() {
        return TaskDto.builder()
                .id(1L)
                .name("name")
                .description("description")
                .lastModifiedDate(Instant.now())
                .build();
    }

    public static List<TaskDto> getTaskList() {
        return Arrays.asList(TaskDto.builder()
                        .id(2L)
                        .name("name2")
                        .description("description2")
                        .lastModifiedDate(Instant.now().minus(1, ChronoUnit.DAYS))
                        .build(),
                TaskDto.builder()
                        .id(1L)
                        .name("name")
                        .description("description")
                        .lastModifiedDate(Instant.now())
                        .build());
    }

    public static TaskDto getTaskDtoForCreation() {
        return TaskDto.builder()
                .name("name")
                .description("description")
                .build();
    }

    public static TaskDto getTaskDtoUpdated() {
        return TaskDto.builder()
                .id(1l)
                .name("nameUpdated")
                .description("descriptionUpdated")
                .lastModifiedDate(Instant.now())
                .build();
    }

    public static TaskDto getInvalidTaskDto() {
        return TaskDto.builder()
                .description("descriptionUpdated")
                .build();
    }
}

package ru.vdsimako.taskmanagement.provider;

import ru.vdsimako.taskmanagement.model.entity.Task;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TaskProvider {
    public static Long getTaskId() {
        return 1L;
    }

    public static Task getTaskFromRepo() {
        return Task.builder()
                .id(1L)
                .name("name")
                .description("description")
                .lastModifiedDate(Instant.now())
                .build();
    }

    public static Task getCreatedTask() {
        return Task.builder()
                .id(1L)
                .name("name")
                .description("description")
                .lastModifiedDate(Instant.now())
                .build();
    }

    public static Optional<Task> getCreatedTaskOptional() {
        return Optional.of(Task.builder()
                .id(1L)
                .name("name")
                .description("description")
                .lastModifiedDate(Instant.now())
                .build());
    }

    public static Task getTaskFromRepoNull() {
        return null;
    }

    public static Optional<Task> getCreatedTaskOptionalEmpty() {
        return Optional.empty();
    }

    public static List<Task> getUnSortedList() {
        return Arrays.asList(
                Task.builder()
                        .id(1L)
                        .name("name")
                        .description("description")
                        .lastModifiedDate(Instant.now())
                        .build(),
                Task.builder()
                        .id(2L)
                        .name("name2")
                        .description("description2")
                        .lastModifiedDate(Instant.now().minus(1, ChronoUnit.DAYS))
                        .build()
        );
    }

    public static List<Task> getSortedList() {
        return Arrays.asList(
                Task.builder()
                        .id(2L)
                        .name("name2")
                        .description("description2")
                        .lastModifiedDate(Instant.now().minus(1, ChronoUnit.DAYS))
                        .build(),
                Task.builder()
                        .id(1L)
                        .name("name")
                        .description("description")
                        .lastModifiedDate(Instant.now())
                        .build()
        );
    }

    public static Task getTaskForCreating() {
        return Task.builder()
                .name("name")
                .description("description")
                .build();
    }

    public static Task getTaskForUpdate() {
        return Task.builder()
                .id(1L)
                .name("nameUpdated")
                .description("descriptionUpdated")
                .build();
    }

    public static Task getTaskFromRepoUpdated() {
        return Task.builder()
                .id(1L)
                .name("nameUpdated")
                .description("descriptionUpdated")
                .lastModifiedDate(Instant.now())
                .build();
    }

    public static Long getInvalidTaskId() {
        return 0L;
    }
}

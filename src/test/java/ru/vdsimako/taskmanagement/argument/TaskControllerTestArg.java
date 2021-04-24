package ru.vdsimako.taskmanagement.argument;

import org.junit.jupiter.params.provider.Arguments;
import ru.vdsimako.taskmanagement.provider.TaskDtoProvider;
import ru.vdsimako.taskmanagement.provider.TaskManagementExceptionProvider;
import ru.vdsimako.taskmanagement.provider.TaskProvider;
import ru.vdsimako.taskmanagement.provider.UpdateTaskDtoProvider;

import java.util.stream.Stream;

public class TaskControllerTestArg {
    public static Stream<Arguments> getTaskById_whenId_thenTaskDto() {
        return Stream.of(
                Arguments.of(
                        TaskProvider.getTaskId(),
                        TaskDtoProvider.getTaskDto()
                )
        );
    }

    public static Stream<Arguments> getTaskById_whenId_thenException() {
        return Stream.of(
                Arguments.of(
                        TaskProvider.getTaskId(),
                        TaskManagementExceptionProvider.getTaskManagementExceptionNotFound()
                )
        );
    }

    public static Stream<Arguments> getTaskList_thenTaskDtoList() {
        return Stream.of(
                Arguments.of(
                        TaskDtoProvider.getTaskList()
                )
        );
    }

    public static Stream<Arguments> createTask_whenTaskDto_thenTaskDto() {
        return Stream.of(
                Arguments.of(
                        TaskDtoProvider.getTaskDtoForCreation(),
                        TaskDtoProvider.getTaskDto()
                )
        );
    }

    public static Stream<Arguments> updateTask_whenUpdateTaskDto_thenTaskDto() {
        return Stream.of(
                Arguments.of(
                        TaskProvider.getTaskId(),
                        UpdateTaskDtoProvider.getUpdateTaskDto(),
                        TaskDtoProvider.getTaskDtoUpdated()
                )
        );
    }

    public static Stream<Arguments> updateTask_whenUpdateTaskDto_thenException() {
        return Stream.of(
                Arguments.of(
                        TaskProvider.getTaskId(),
                        UpdateTaskDtoProvider.getUpdateTaskDto(),
                        TaskManagementExceptionProvider.getTaskManagementExceptionNotFound()
                )
        );
    }

    public static Stream<Arguments> deleteTaskById_whenTaskId_thenTaskDto() {
        return Stream.of(
                Arguments.of(
                        TaskProvider.getTaskId(),
                        TaskDtoProvider.getTaskDto()
                )
        );
    }

    public static Stream<Arguments> deleteTaskById_whenTaskId_thenException() {
        return Stream.of(
                Arguments.of(
                        TaskProvider.getTaskId(),
                        TaskManagementExceptionProvider.getTaskManagementExceptionNotFound()
                )
        );
    }
}

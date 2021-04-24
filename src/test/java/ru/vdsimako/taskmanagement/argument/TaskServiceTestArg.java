package ru.vdsimako.taskmanagement.argument;

import org.junit.jupiter.params.provider.Arguments;
import ru.vdsimako.taskmanagement.provider.TaskDtoProvider;
import ru.vdsimako.taskmanagement.provider.TaskManagementExceptionProvider;
import ru.vdsimako.taskmanagement.provider.TaskProvider;
import ru.vdsimako.taskmanagement.provider.UpdateTaskDtoProvider;

import java.util.stream.Stream;

public class TaskServiceTestArg {
    public static Stream<Arguments> getTaskById_whenId_thenTaskDto() {
        return Stream.of(
                Arguments.of(
                        TaskProvider.getTaskId(),
                        TaskProvider.getCreatedTaskOptional(),
                        TaskDtoProvider.getTaskDto()
                )
        );
    }

    public static Stream<Arguments> getTask_whenId_thenException() {
        return Stream.of(
                Arguments.of(
                        TaskProvider.getTaskId(),
                        TaskProvider.getCreatedTaskOptionalEmpty(),
                        TaskManagementExceptionProvider.getTaskManagementExceptionNotFound()
                )
        );
    }

    public static Stream<Arguments> getTaskList_whenTaskDtoList() {
        return Stream.of(
                Arguments.of(
                        TaskProvider.getSortedList(),
                        TaskDtoProvider.getTaskList()
                )
        );
    }

    public static Stream<Arguments> updateTask_whenUpdateTaskDto_thenTaskDto() {
        return Stream.of(
                Arguments.of(
                        TaskProvider.getTaskForUpdate(),
                        TaskProvider.getTaskFromRepoUpdated(),
                        TaskProvider.getTaskId(),
                        UpdateTaskDtoProvider.getUpdateTaskDto(),
                        TaskDtoProvider.getTaskDtoUpdated()
                )
        );
    }

    public static Stream<Arguments> createTask_whenTaskDto_thenTaskDto() {
        return Stream.of(
                Arguments.of(
                        TaskProvider.getTaskForCreating(),
                        TaskProvider.getTaskFromRepo(),
                        TaskDtoProvider.getTaskDtoForCreation(),
                        TaskDtoProvider.getTaskDto()
                )
        );
    }

    public static Stream<Arguments> updateTask_whenUpdateTaskDto_thenException() {
        return Stream.of(
                Arguments.of(
                        TaskProvider.getTaskForUpdate(),
                        TaskManagementExceptionProvider.getTaskManagementExceptionNotFound(),
                        TaskProvider.getTaskId(),
                        UpdateTaskDtoProvider.getUpdateTaskDto()
                )
        );
    }

    public static Stream<Arguments> deleteTask_whenTaskId_thenTaskDto() {
        return Stream.of(
                Arguments.of(
                        TaskProvider.getTaskId(),
                        TaskProvider.getTaskFromRepo(),
                        TaskDtoProvider.getTaskDto()
                )
        );
    }

    public static Stream<Arguments> deleteTask_whenTaskId_thenException() {
        return Stream.of(
                Arguments.of(
                        TaskProvider.getTaskId(),
                        TaskManagementExceptionProvider.getTaskManagementExceptionNotFound()
                )
        );
    }
}

package ru.vdsimako.taskmanagement.argument;

import org.junit.jupiter.params.provider.Arguments;
import ru.vdsimako.taskmanagement.provider.TaskDtoProvider;
import ru.vdsimako.taskmanagement.provider.TaskManagementExceptionDtoProvider;
import ru.vdsimako.taskmanagement.provider.TaskProvider;
import ru.vdsimako.taskmanagement.provider.UpdateTaskDtoProvider;

import java.util.stream.Stream;

public class TaskControllerMvcTestArg {
    public static Stream<Arguments> getTaskById_whenId_thenTaskDto() {
        return Stream.of(
                Arguments.of(
                        TaskProvider.getTaskId(),
                        TaskProvider.getTaskFromRepo(),
                        TaskDtoProvider.getTaskDto()
                )
        );
    }

    public static Stream<Arguments> getTaskById_whenId_thenException() {
        return Stream.of(
                Arguments.of(
                        TaskProvider.getTaskId(),
                        TaskManagementExceptionDtoProvider.getTaskManagementExceptionDtoNotFound()
                )
        );
    }

    public static Stream<Arguments> getTaskList_thenTaskDtoList() {
        return Stream.of(
                Arguments.of(
                        TaskProvider.getUnSortedList(),
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

    public static Stream<Arguments> createTask_whenTaskDto_thenBadRequest() {
        return Stream.of(
                Arguments.of(
                        TaskDtoProvider.getInvalidTaskDto()
                )
        );
    }

    public static Stream<Arguments> updateTask_whenUpdateTaskDto_thenTaskDto() {
        return Stream.of(
                Arguments.of(
                        TaskProvider.getTaskId(),
                        TaskProvider.getTaskFromRepoUpdated(),
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
                        TaskManagementExceptionDtoProvider.getTaskManagementExceptionDtoNotFound()
                )
        );
    }

    public static Stream<Arguments> updateTask_whenUpdateTaskDto_thenBadRequest() {
        return Stream.of(
                Arguments.of(
                        TaskProvider.getInvalidTaskId(),
                        UpdateTaskDtoProvider.getUpdateTaskDto()
                ),
                Arguments.of(
                        TaskProvider.getInvalidTaskId(),
                        UpdateTaskDtoProvider.getInvalidUpdateTaskDto()
                )
        );
    }

    public static Stream<Arguments> deleteTaskById_whenTaskId_thenTaskDto() {
        return Stream.of(
                Arguments.of(
                        TaskProvider.getTaskId(),
                        TaskProvider.getTaskFromRepo(),
                        TaskDtoProvider.getTaskDto()
                )
        );
    }

    public static Stream<Arguments> deleteTaskById_whenTaskId_thenException() {
        return Stream.of(
                Arguments.of(
                        TaskProvider.getTaskId(),
                        TaskManagementExceptionDtoProvider.getTaskManagementExceptionDtoNotFound()
                )
        );
    }

    public static Stream<Arguments> deleteTaskById_whenTaskId_thenBadRequest() {
        return Stream.of(
                Arguments.of(
                        TaskProvider.getInvalidTaskId()
                )
        );
    }
}

package ru.vdsimako.taskmanagement.argument;

import org.junit.jupiter.params.provider.Arguments;
import ru.vdsimako.taskmanagement.provider.TaskManagementExceptionProvider;
import ru.vdsimako.taskmanagement.provider.TaskProvider;

import java.util.stream.Stream;

public class TaskRepositoryTestArg {

    public static Stream<Arguments> getTask_whenTaskId_thenTask() {
        return Stream.of(
                Arguments.of(
                        TaskProvider.getTaskId(),
                        TaskProvider.getTaskFromRepo(),
                        TaskProvider.getCreatedTaskOptional()
                ),
                Arguments.of(
                        TaskProvider.getTaskId(),
                        TaskProvider.getTaskFromRepoNull(),
                        TaskProvider.getCreatedTaskOptionalEmpty()
                )
        );
    }

    public static Stream<Arguments> getTaskList_thenTaskList() {
        return Stream.of(
                Arguments.of(
                        TaskProvider.getUnSortedList(),
                        TaskProvider.getSortedList()
                )
        );
    }

    public static Stream<Arguments> createTask_whenTask_thenCreatedTask() {
        return Stream.of(
                Arguments.of(
                        TaskProvider.getTaskId(),
                        TaskProvider.getTaskForCreating(),
                        TaskProvider.getTaskFromRepo()
                )
        );
    }

    public static Stream<Arguments> updateTask_whenTask_thenUpdatedTask() {
        return Stream.of(
                Arguments.of(
                        TaskProvider.getTaskId(),
                        TaskProvider.getTaskFromRepo(),
                        TaskProvider.getTaskForUpdate(),
                        TaskProvider.getTaskFromRepoUpdated()
                )
        );
    }

    public static Stream<Arguments> updateTask_whenTask_thenException() {
        return Stream.of(
                Arguments.of(
                        TaskProvider.getTaskId(),
                        TaskProvider.getTaskForUpdate(),
                        TaskManagementExceptionProvider.getTaskManagementExceptionNotFound()
                )
        );
    }

    public static Stream<Arguments> deleteTaskById_whenTaskId_thenTask() {
        return Stream.of(
                Arguments.of(
                        TaskProvider.getTaskId(),
                        TaskProvider.getTaskFromRepo(),
                        TaskProvider.getTaskFromRepo()
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

package ru.vdsimako.taskmanagement.repository;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.vdsimako.taskmanagement.model.entity.Task;
import ru.vdsimako.taskmanagement.model.exception.TaskManagementException;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskRepositoryTest {
    private final Map<Long, Task> taskMap = mock(Map.class);
    private final AtomicLong taskSequence = new AtomicLong(0L);
    private final TaskRepository taskRepository = new TaskRepository(taskMap, taskSequence);

    @ParameterizedTest
    @MethodSource("ru.vdsimako.taskmanagement.argument.TaskRepositoryTestArg#getTask_whenTaskId_thenTask")
    void getTask_whenTaskId_thenTask(Long taskId,
                                     Task task,
                                     Optional<Task> taskOptionalExpected) {
        when(taskMap.get(taskId)).thenReturn(task);

        Optional<Task> taskActual = taskRepository.getTask(taskId);

        assertEquals(taskOptionalExpected, taskActual);
    }

    @ParameterizedTest
    @MethodSource("ru.vdsimako.taskmanagement.argument.TaskRepositoryTestArg#getTaskList_thenTaskList")
    void getTaskList_thenTaskList(List<Task> taskList,
                                  List<Task> taskListExpected) {
        when(taskMap.values()).thenReturn(taskList);

        List<Task> taskListActual = taskRepository.getTaskList();

        assertEquals(taskListExpected, taskListActual);
    }

    @ParameterizedTest
    @MethodSource("ru.vdsimako.taskmanagement.argument.TaskRepositoryTestArg#createTask_whenTask_thenCreatedTask")
    void createTask_whenTask_thenCreatedTask(Long taskId,
                                             Task task,
                                             Task taskExpected) {
        when(taskMap.put(taskId, task)).thenReturn(task);

        Task taskActual = taskRepository.createTask(task);

        assertThat(taskActual)
                .usingRecursiveComparison()
                .ignoringFields("lastModifiedDate")
                .isEqualTo(taskExpected);
    }

    @ParameterizedTest
    @MethodSource("ru.vdsimako.taskmanagement.argument.TaskRepositoryTestArg#updateTask_whenTask_thenUpdatedTask")
    void updateTask_whenTask_thenUpdatedTask(Long taskId,
                                             Task task,
                                             Task taskForUpdate,
                                             Task taskExpected) {
        when(taskMap.containsKey(taskId)).thenReturn(true);
        when(taskMap.get(taskId)).thenReturn(task);

        Task taskActual = taskRepository.updateTask(taskForUpdate);

        assertThat(taskActual)
                .usingRecursiveComparison()
                .ignoringFields("lastModifiedDate")
                .isEqualTo(taskExpected);
    }

    @ParameterizedTest
    @MethodSource("ru.vdsimako.taskmanagement.argument.TaskRepositoryTestArg#updateTask_whenTask_thenException")
    void updateTask_whenTask_thenException(Long taskId,
                                           Task taskForUpdate,
                                           TaskManagementException taskManagementExceptionExpected) {
        when(taskMap.containsKey(taskId)).thenReturn(false);

        TaskManagementException taskManagementExceptionActual = assertThrows(TaskManagementException.class, () -> taskRepository.updateTask(taskForUpdate));

        assertEquals(taskManagementExceptionExpected.getExceptionMessage(), taskManagementExceptionActual.getExceptionMessage());
    }

    @ParameterizedTest
    @MethodSource("ru.vdsimako.taskmanagement.argument.TaskRepositoryTestArg#deleteTaskById_whenTaskId_thenTask")
    void deleteTaskById_whenTaskId_thenTask(Long taskId,
                                            Task task,
                                            Task taskExpected) {
        when(taskMap.containsKey(taskId)).thenReturn(true);
        when(taskMap.remove(taskId)).thenReturn(task);

        Task taskActual = taskRepository.deleteTaskById(taskId);

        assertThat(taskActual)
                .usingRecursiveComparison()
                .ignoringFields("lastModifiedDate")
                .isEqualTo(taskExpected);
    }

    @ParameterizedTest
    @MethodSource("ru.vdsimako.taskmanagement.argument.TaskRepositoryTestArg#deleteTaskById_whenTaskId_thenException")
    void deleteTaskById_whenTaskId_thenException(Long taskId,
                                                 TaskManagementException taskManagementExceptionExpected) {
        when(taskMap.containsKey(taskId)).thenReturn(false);

        TaskManagementException taskManagementExceptionActual = assertThrows(TaskManagementException.class, () -> taskRepository.deleteTaskById(taskId));

        assertEquals(taskManagementExceptionExpected.getExceptionMessage(), taskManagementExceptionActual.getExceptionMessage());
    }
}
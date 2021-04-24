package ru.vdsimako.taskmanagement.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.vdsimako.taskmanagement.model.dto.TaskDto;
import ru.vdsimako.taskmanagement.model.dto.UpdateTaskDto;
import ru.vdsimako.taskmanagement.model.entity.Task;
import ru.vdsimako.taskmanagement.model.exception.TaskManagementException;
import ru.vdsimako.taskmanagement.repository.ITaskRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {
    @Mock
    private ITaskRepository taskRepository;
    @InjectMocks
    private TaskService taskService;

    @ParameterizedTest
    @MethodSource("ru.vdsimako.taskmanagement.argument.TaskServiceTestArg#getTaskById_whenId_thenTaskDto")
    void getTask_whenId_thenTaskDto(Long taskId,
                                    Optional<Task> taskOptional,
                                    TaskDto taskDtoExpected) {
        when(taskRepository.getTask(taskId)).thenReturn(taskOptional);

        TaskDto taskDtoActual = taskService.getTask(taskId);

        assertThat(taskDtoActual)
                .usingRecursiveComparison()
                .ignoringFields("lastModifiedDate")
                .isEqualTo(taskDtoExpected);
    }

    @ParameterizedTest
    @MethodSource("ru.vdsimako.taskmanagement.argument.TaskServiceTestArg#getTask_whenId_thenException")
    void getTask_whenId_thenException(Long taskId,
                                      Optional<Task> taskOptional,
                                      TaskManagementException taskManagementExceptionExpected) {
        when(taskRepository.getTask(taskId)).thenReturn(taskOptional);

        TaskManagementException taskManagementExceptionActual = assertThrows(TaskManagementException.class, () -> taskService.getTask(taskId));

        assertEquals(taskManagementExceptionExpected.getExceptionMessage(), taskManagementExceptionActual.getExceptionMessage());
    }

    @ParameterizedTest
    @MethodSource("ru.vdsimako.taskmanagement.argument.TaskServiceTestArg#getTaskList_whenTaskDtoList")
    void getTaskList_whenTaskDtoList(List<Task> taskList,
                                     List<TaskDto> taskDtoListExpected) {
        when(taskRepository.getTaskList()).thenReturn(taskList);

        List<TaskDto> taskDtoListActual = taskService.getTaskList();

        assertThat(taskDtoListActual)
                .usingRecursiveComparison()
                .ignoringFields("lastModifiedDate")
                .isEqualTo(taskDtoListExpected);
    }

    @ParameterizedTest
    @MethodSource("ru.vdsimako.taskmanagement.argument.TaskServiceTestArg#createTask_whenTaskDto_thenTaskDto")
    void createTask_whenTaskDto_thenTaskDto(Task task,
                                            Task taskCreated,
                                            TaskDto taskForCreation,
                                            TaskDto taskDtoExpected) {
        when(taskRepository.createTask(task)).thenReturn(taskCreated);

        TaskDto taskDtoActual = taskService.createTask(taskForCreation);

        assertThat(taskDtoActual)
                .usingRecursiveComparison()
                .ignoringFields("lastModifiedDate")
                .isEqualTo(taskDtoExpected);
    }

    @ParameterizedTest
    @MethodSource("ru.vdsimako.taskmanagement.argument.TaskServiceTestArg#updateTask_whenUpdateTaskDto_thenTaskDto")
    void updateTask_whenUpdateTaskDto_thenTaskDto(Task task,
                                                  Task taskUpdated,
                                                  Long taskId,
                                                  UpdateTaskDto updateTaskDto,
                                                  TaskDto taskDtoExpected) {
        when(taskRepository.updateTask(task)).thenReturn(taskUpdated);

        TaskDto taskDtoActual = taskService.updateTask(taskId, updateTaskDto);

        assertThat(taskDtoActual)
                .usingRecursiveComparison()
                .ignoringFields("lastModifiedDate")
                .isEqualTo(taskDtoExpected);
    }

    @ParameterizedTest
    @MethodSource("ru.vdsimako.taskmanagement.argument.TaskServiceTestArg#updateTask_whenUpdateTaskDto_thenException")
    void updateTask_whenUpdateTaskDto_thenException(Task task,
                                                    TaskManagementException taskManagementExceptionExpected,
                                                    Long taskId,
                                                    UpdateTaskDto updateTaskDto) {
        when(taskRepository.updateTask(task)).thenThrow(taskManagementExceptionExpected);

        TaskManagementException taskManagementExceptionActual = assertThrows(TaskManagementException.class, () -> taskService.updateTask(taskId, updateTaskDto));

        assertEquals(taskManagementExceptionExpected.getExceptionMessage(), taskManagementExceptionActual.getExceptionMessage());
    }

    @ParameterizedTest
    @MethodSource("ru.vdsimako.taskmanagement.argument.TaskServiceTestArg#deleteTask_whenTaskId_thenTaskDto")
    void deleteTask_whenTaskId_thenTaskDto(Long taskId,
                                           Task task,
                                           TaskDto taskDtoExpected) {
        when(taskRepository.deleteTaskById(taskId)).thenReturn(task);

        TaskDto taskDtoActual = taskService.deleteTask(taskId);

        assertThat(taskDtoActual)
                .usingRecursiveComparison()
                .ignoringFields("lastModifiedDate")
                .isEqualTo(taskDtoExpected);

    }

    @ParameterizedTest
    @MethodSource("ru.vdsimako.taskmanagement.argument.TaskServiceTestArg#deleteTask_whenTaskId_thenException")
    void deleteTask_whenTaskId_thenException(Long taskId,
                                             TaskManagementException taskManagementExceptionExpected) {
        when(taskRepository.deleteTaskById(taskId)).thenThrow(taskManagementExceptionExpected);

        TaskManagementException taskManagementExceptionActual = assertThrows(TaskManagementException.class, () -> taskService.deleteTask(taskId));

        assertEquals(taskManagementExceptionExpected.getExceptionMessage(), taskManagementExceptionActual.getExceptionMessage());
    }
}
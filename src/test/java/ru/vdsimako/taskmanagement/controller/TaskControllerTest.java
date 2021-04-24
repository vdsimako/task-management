package ru.vdsimako.taskmanagement.controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.vdsimako.taskmanagement.model.dto.TaskDto;
import ru.vdsimako.taskmanagement.model.dto.UpdateTaskDto;
import ru.vdsimako.taskmanagement.model.exception.TaskManagementException;
import ru.vdsimako.taskmanagement.service.ITaskService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {

    @Mock
    private ITaskService taskService;
    @InjectMocks
    private TaskController taskController;

    @ParameterizedTest
    @MethodSource("ru.vdsimako.taskmanagement.argument.TaskControllerTestArg#getTaskById_whenId_thenTaskDto")
    void getTaskById_whenId_thenTaskDto(Long taskId,
                                        TaskDto taskDtoExpected) {
        when(taskService.getTask(taskId)).thenReturn(taskDtoExpected);

        TaskDto taskDtoActual = taskController.getTaskById(taskId);

        assertEquals(taskDtoExpected, taskDtoActual);
    }

    @ParameterizedTest
    @MethodSource("ru.vdsimako.taskmanagement.argument.TaskControllerTestArg#getTaskById_whenId_thenException")
    void getTaskById_whenId_thenException(Long taskId,
                                          TaskManagementException taskManagementExceptionExpected) {
        when(taskService.getTask(taskId)).thenThrow(taskManagementExceptionExpected);

        TaskManagementException taskManagementExceptionActual
                = assertThrows(TaskManagementException.class, () -> taskController.getTaskById(taskId));

        assertEquals(taskManagementExceptionExpected.getExceptionMessage(), taskManagementExceptionActual.getExceptionMessage());
    }

    @ParameterizedTest
    @MethodSource("ru.vdsimako.taskmanagement.argument.TaskControllerTestArg#getTaskList_thenTaskDtoList")
    void getTaskList_thenTaskDtoList(List<TaskDto> taskDtoListExpected) {
        when(taskService.getTaskList()).thenReturn(taskDtoListExpected);

        List<TaskDto> taskListActual = taskController.getTaskList();

        assertEquals(taskDtoListExpected, taskListActual);
    }

    @ParameterizedTest
    @MethodSource("ru.vdsimako.taskmanagement.argument.TaskControllerTestArg#createTask_whenTaskDto_thenTaskDto")
    void createTask_whenTaskDto_thenTaskDto(TaskDto taskDto,
                                            TaskDto taskDtoExpected) {
        when(taskService.createTask(taskDto)).thenReturn(taskDtoExpected);

        TaskDto taskDtoActual = taskController.createTask(taskDto);

        assertEquals(taskDtoExpected, taskDtoActual);
    }

    @ParameterizedTest
    @MethodSource("ru.vdsimako.taskmanagement.argument.TaskControllerTestArg#updateTask_whenUpdateTaskDto_thenTaskDto")
    void updateTask_whenUpdateTaskDto_thenTaskDto(Long taskId,
                                                  UpdateTaskDto updateTaskDto,
                                                  TaskDto taskDtoExpected) {
        when(taskService.updateTask(taskId, updateTaskDto)).thenReturn(taskDtoExpected);

        TaskDto taskDtoActual = taskController.updateTask(taskId, updateTaskDto);

        assertEquals(taskDtoExpected, taskDtoActual);
    }

    @ParameterizedTest
    @MethodSource("ru.vdsimako.taskmanagement.argument.TaskControllerTestArg#updateTask_whenUpdateTaskDto_thenException")
    void updateTask_whenUpdateTaskDto_thenException(Long taskId,
                                                    UpdateTaskDto updateTaskDto,
                                                    TaskManagementException taskManagementExceptionExpected) {
        when(taskService.updateTask(taskId, updateTaskDto)).thenThrow(taskManagementExceptionExpected);

        TaskManagementException taskManagementExceptionActual =
                assertThrows(TaskManagementException.class, () -> taskController.updateTask(taskId, updateTaskDto));

        assertEquals(taskManagementExceptionExpected.getExceptionMessage(), taskManagementExceptionActual.getExceptionMessage());
    }

    @ParameterizedTest
    @MethodSource("ru.vdsimako.taskmanagement.argument.TaskControllerTestArg#deleteTaskById_whenTaskId_thenTaskDto")
    void deleteTaskById_whenTaskId_thenTaskDto(Long taskId,
                                               TaskDto taskDtoExpected) {
        when(taskService.deleteTask(taskId)).thenReturn(taskDtoExpected);

        TaskDto taskDtoActual = taskController.deleteTaskById(taskId);

        assertEquals(taskDtoExpected, taskDtoActual);
    }

    @ParameterizedTest
    @MethodSource("ru.vdsimako.taskmanagement.argument.TaskControllerTestArg#deleteTaskById_whenTaskId_thenException")
    void deleteTaskById_whenTaskId_thenException(Long taskId,
                                                 TaskManagementException taskManagementExceptionExpected) {
        when(taskService.deleteTask(taskId)).thenThrow(taskManagementExceptionExpected);

        TaskManagementException taskManagementExceptionActual
                = assertThrows(TaskManagementException.class, () -> taskController.deleteTaskById(taskId));

        assertEquals(taskManagementExceptionExpected.getExceptionMessage(), taskManagementExceptionActual.getExceptionMessage());
    }
}
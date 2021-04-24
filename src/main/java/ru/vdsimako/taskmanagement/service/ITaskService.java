package ru.vdsimako.taskmanagement.service;

import ru.vdsimako.taskmanagement.model.dto.TaskDto;
import ru.vdsimako.taskmanagement.model.dto.UpdateTaskDto;

import java.util.List;

public interface ITaskService {
    TaskDto getTask(Long id);

    List<TaskDto> getTaskList();

    TaskDto createTask(TaskDto taskDto);

    TaskDto updateTask(Long taskId, UpdateTaskDto updateTaskDto);

    TaskDto deleteTask(Long id);
}

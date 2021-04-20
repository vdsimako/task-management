package ru.vdsimako.taskmanagement.service;

import org.springframework.stereotype.Service;
import ru.vdsimako.taskmanagement.model.dto.TaskDto;
import ru.vdsimako.taskmanagement.model.dto.UpdateTaskDto;

import java.util.List;

@Service
public class TaskService implements ITaskService {
    @Override
    public TaskDto getTask(Long id) {
        return null;
    }

    @Override
    public List<TaskDto> getTaskList() {
        return null;
    }

    @Override
    public TaskDto createTask(TaskDto taskDto) {
        return null;
    }

    @Override
    public TaskDto updateTask(UpdateTaskDto updateTaskDto) {
        return null;
    }

    @Override
    public TaskDto deleteTask(Long id) {
        return null;
    }
}

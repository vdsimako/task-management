package ru.vdsimako.taskmanagement.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vdsimako.taskmanagement.model.dto.TaskDto;
import ru.vdsimako.taskmanagement.model.dto.UpdateTaskDto;
import ru.vdsimako.taskmanagement.model.entity.Task;
import ru.vdsimako.taskmanagement.model.exception.ExceptionMessage;
import ru.vdsimako.taskmanagement.model.exception.TaskManagementException;
import ru.vdsimako.taskmanagement.repository.ITaskRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService implements ITaskService {

    private final ITaskRepository taskRepository;

    public TaskService(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskDto getTask(Long id) {
        Task task = taskRepository.getTask(id)
                .orElseThrow(() -> new TaskManagementException(ExceptionMessage.TASK_NOT_FOUND));

        TaskDto taskDto = TaskDto.builder()
                .id(task.getId())
                .name(task.getName())
                .description(task.getDescription())
                .lastModifiedDate(task.getLastModifiedDate())
                .build();

        return taskDto;
    }

    @Override
    public List<TaskDto> getTaskList() {

        List<Task> taskList = taskRepository.getTaskList();

        List<TaskDto> taskDtoList = taskList.stream().map(task -> TaskDto.builder()
                .id(task.getId())
                .name(task.getName())
                .description(task.getDescription())
                .lastModifiedDate(task.getLastModifiedDate())
                .build())
                .collect(Collectors.toList());

        return taskDtoList;
    }

    @Override
    @Transactional
    public TaskDto createTask(TaskDto taskDto) {
        Task task = Task.builder()
                .name(taskDto.getName())
                .description(taskDto.getDescription())
                .build();

        task = taskRepository.createTask(task);

        taskDto.setId(task.getId());
        taskDto.setLastModifiedDate(task.getLastModifiedDate());
        return taskDto;
    }

    @Override
    @Transactional
    public TaskDto updateTask(Long taskId,
                              UpdateTaskDto updateTaskDto) {
        Task task = Task.builder()
                .id(taskId)
                .name(updateTaskDto.getName())
                .description(updateTaskDto.getDescription())
                .build();

        task = taskRepository.updateTask(task);

        TaskDto taskDto = TaskDto.builder()
                .id(task.getId())
                .name(task.getName())
                .description(task.getDescription())
                .lastModifiedDate(task.getLastModifiedDate())
                .build();

        return taskDto;
    }

    @Override
    @Transactional
    public TaskDto deleteTask(Long id) {
        Task task = taskRepository.deleteTaskById(id);

        TaskDto taskDto = TaskDto.builder()
                .id(task.getId())
                .name(task.getName())
                .description(task.getDescription())
                .lastModifiedDate(task.getLastModifiedDate())
                .build();

        return taskDto;
    }
}

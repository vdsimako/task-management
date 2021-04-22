package ru.vdsimako.taskmanagement.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vdsimako.taskmanagement.model.dto.TaskDto;
import ru.vdsimako.taskmanagement.model.dto.UpdateTaskDto;
import ru.vdsimako.taskmanagement.service.ITaskService;
import ru.vdsimako.taskmanagement.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final ITaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/{id}")
    public TaskDto getTaskById(@PathVariable(name = "id") Long id) {

        TaskDto taskDto = taskService.getTask(id);

        return taskDto;
    }

    @GetMapping
    public List<TaskDto> getTaskList() {

        List<TaskDto> taskDtoList = taskService.getTaskList();

        return taskDtoList;
    }

    @PostMapping
    public TaskDto createTask(TaskDto taskDto) {

        taskDto = taskService.createTask(taskDto);

        return taskDto;
    }

    @PutMapping("/{id}")
    public TaskDto updateTask(@PathVariable(name = "id") Long taskId,
                              @RequestBody UpdateTaskDto updateTaskDto) {

        TaskDto taskDto = taskService.updateTask(taskId, updateTaskDto);

        return taskDto;
    }

    @DeleteMapping("/{id}")
    public TaskDto deleteTaskById(@PathVariable(name = "id") Long id) {

        TaskDto taskDto = taskService.deleteTask(id);

        return taskDto;
    }


}

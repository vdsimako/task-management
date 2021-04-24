package ru.vdsimako.taskmanagement.repository;

import ru.vdsimako.taskmanagement.model.entity.Task;

import java.util.List;
import java.util.Optional;

public interface ITaskRepository {
    Optional<Task> getTask(Long id);

    List<Task> getTaskList();

    Task createTask(Task task);

    Task updateTask(Task task);

    Task deleteTaskById(Long id);
}

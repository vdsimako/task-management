package ru.vdsimako.taskmanagement.repository;

import org.springframework.stereotype.Service;
import ru.vdsimako.taskmanagement.model.entity.Task;
import ru.vdsimako.taskmanagement.model.exception.ExceptionMessage;
import ru.vdsimako.taskmanagement.model.exception.TaskManagementException;

import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class TaskRepository implements ITaskRepository {
    private final Map<Long, Task> taskMap;
    private final AtomicLong taskSequence;

    public TaskRepository(Map<Long, Task> taskMap,
                          AtomicLong taskSequence) {
        this.taskMap = taskMap;
        this.taskSequence = taskSequence;
    }


    @Override
    public Optional<Task> getTask(Long id) {

        Optional<Task> task = Optional.ofNullable(taskMap.get(id));

        return task;
    }

    @Override
    public List<Task> getTaskList() {

        List<Task> taskList = taskMap.values()
                .stream()
                .sorted(Comparator.comparing(Task::getLastModifiedDate))
                .collect(Collectors.toList());

        return taskList;
    }

    @Override
    public Task createTask(Task task) {
        long taskId = taskSequence.incrementAndGet();

        task.setId(taskId);
        task.setLastModifiedDate(Instant.now());

        taskMap.put(taskId, task);

        return task;
    }

    @Override
    public Task updateTask(Task taskForUpdate) {
        Task task;

        if (taskMap.containsKey(taskForUpdate.getId())) {
            task = taskMap.get(taskForUpdate.getId());

            task.setLastModifiedDate(Instant.now());

            task.setName(taskForUpdate.getName());

            task.setDescription(taskForUpdate.getDescription());

        } else {
            throw new TaskManagementException(ExceptionMessage.TASK_NOT_FOUND);
        }

        return task;
    }

    @Override
    public Task deleteTaskById(Long id) {
        Task task;

        if (taskMap.containsKey(id)) {
            task = taskMap.remove(id);
        } else {
            throw new TaskManagementException(ExceptionMessage.TASK_NOT_FOUND);
        }

        return task;
    }
}

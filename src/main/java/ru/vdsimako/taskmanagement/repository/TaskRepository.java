package ru.vdsimako.taskmanagement.repository;

import org.springframework.stereotype.Service;
import ru.vdsimako.taskmanagement.model.entity.Task;
import ru.vdsimako.taskmanagement.model.exception.ExceptionMessage;
import ru.vdsimako.taskmanagement.model.exception.TaskManagementException;

import java.time.Instant;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class TaskRepository implements ITaskRepository {
    private static final Map<Long, Task> mapOfTask = Collections.synchronizedMap(new HashMap<>());
    private static final AtomicLong taskSequence = new AtomicLong(0L);


    @Override
    public Optional<Task> getTask(Long id) {

        Optional<Task> task = Optional.ofNullable(mapOfTask.get(id));

        return task;
    }

    @Override
    public List<Task> getTaskList() {

        List<Task> taskList = mapOfTask.values()
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

        mapOfTask.putIfAbsent(taskId, task);

        return task;
    }

    @Override
    public Task updateTask(Task task) {

        if (mapOfTask.containsKey(task.getId())) {
            task = mapOfTask.get(task.getId());

            task.setLastModifiedDate(Instant.now());

        } else {
            throw new TaskManagementException(ExceptionMessage.TASK_NOT_FOUND);
        }

        return task;
    }

    @Override
    public Task deleteTaskById(Long id) {
        Task remove;

        if (mapOfTask.containsKey(id)) {
            remove = mapOfTask.remove(id);
        } else {
            throw new TaskManagementException(ExceptionMessage.TASK_NOT_FOUND);
        }

        return remove;
    }
}

package ru.vdsimako.taskmanagement.provider;

import ru.vdsimako.taskmanagement.model.exception.ExceptionMessage;
import ru.vdsimako.taskmanagement.model.exception.TaskManagementException;

public class TaskManagementExceptionProvider {
        public static TaskManagementException getTaskManagementExceptionNotFound() {
                return new TaskManagementException(ExceptionMessage.TASK_NOT_FOUND);
        }
}

package ru.vdsimako.taskmanagement.provider;

import ru.vdsimako.taskmanagement.model.dto.TaskManagementExceptionDto;
import ru.vdsimako.taskmanagement.model.exception.ExceptionMessage;

public class TaskManagementExceptionDtoProvider {

    public static TaskManagementExceptionDto getTaskManagementExceptionDtoNotFound() {
        return TaskManagementExceptionDto.builder()
                .exceptionMessage(ExceptionMessage.TASK_NOT_FOUND)
                .description(ExceptionMessage.TASK_NOT_FOUND.getDescription())
                .build();
    }
}

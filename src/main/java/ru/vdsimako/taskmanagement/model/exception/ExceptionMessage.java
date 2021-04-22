package ru.vdsimako.taskmanagement.model.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionMessage {

    TASK_NOT_FOUND(HttpStatus.NOT_FOUND, "Task not found");

    private HttpStatus httpStatus;
    private String description;

    ExceptionMessage(HttpStatus httpStatus,
                     String description) {
        this.httpStatus = httpStatus;
        this.description = description;
    }

}

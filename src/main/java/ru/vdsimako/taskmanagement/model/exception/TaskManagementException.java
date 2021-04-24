package ru.vdsimako.taskmanagement.model.exception;


import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TaskManagementException extends RuntimeException {
    private ExceptionMessage exceptionMessage;

    public TaskManagementException(ExceptionMessage exceptionMessage) {
        super();
        this.exceptionMessage = exceptionMessage;
    }
}

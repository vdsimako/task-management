package ru.vdsimako.taskmanagement.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.vdsimako.taskmanagement.model.dto.TaskManagementExceptionDto;
import ru.vdsimako.taskmanagement.model.exception.TaskManagementException;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
@Log4j2
public class ExceptionAdvice {

    @ExceptionHandler(Exception.class)
    protected ResponseEntity handleException(Exception ex) {
        log.error("Catch exception", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity handleException(MethodArgumentNotValidException ex) {
        log.error("Catch exception", ex);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity handleException(ConstraintViolationException ex) {
        log.error("Catch exception", ex);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .build();
    }

    @ExceptionHandler(TaskManagementException.class)
    protected ResponseEntity handleException(TaskManagementException ex) {
        log.error("Catch exception", ex);
        return ResponseEntity
                .status(ex.getExceptionMessage().getHttpStatus())
                .body(TaskManagementExceptionDto.builder()
                        .exceptionMessage(ex.getExceptionMessage())
                        .description(ex.getExceptionMessage().getDescription())
                        .build());
    }
}

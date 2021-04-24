package ru.vdsimako.taskmanagement.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.vdsimako.taskmanagement.model.exception.ExceptionMessage;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TaskManagementExceptionDto {
    private ExceptionMessage exceptionMessage;
    private String description;
}

package ru.vdsimako.taskmanagement.provider;

import ru.vdsimako.taskmanagement.model.dto.UpdateTaskDto;

public class UpdateTaskDtoProvider {
    public static UpdateTaskDto getUpdateTaskDto() {
        return UpdateTaskDto.builder()
                .name("nameUpdated")
                .description("descriptionUpdated")
                .build();
    }

    public static UpdateTaskDto getInvalidUpdateTaskDto() {
        return UpdateTaskDto.builder()
                .description("descriptionUpdated")
                .build();
    }
}

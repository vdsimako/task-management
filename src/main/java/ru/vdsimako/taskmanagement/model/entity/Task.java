package ru.vdsimako.taskmanagement.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Task {
    private Long id;
    private String name;
    private String description;
    private Instant lastModifiedDate;
}

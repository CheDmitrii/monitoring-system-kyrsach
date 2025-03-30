package ru.system.library.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ReferenceDTO {
    private UUID id;
    private UUID sensor_id;
    private String name;
    private double value;
    private String type;
}

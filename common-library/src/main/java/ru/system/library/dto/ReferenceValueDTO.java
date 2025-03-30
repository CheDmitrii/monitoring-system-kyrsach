package ru.system.library.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ReferenceValueDTO {
    private UUID id;
    private UUID sensorId;
    private String name;
    private double value;
    private String type;
}

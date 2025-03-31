package ru.system.library.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class CreateSensorDTO {
    private UUID id;
    @NotNull
    private String name;
    private String description;
    @NotNull
    private String type;
    @NotNull
    private Double referenceValue;
}

package ru.system.monitoring.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class RequestUpdateReferenceDTO {
    @NotNull
    private UUID id;
    @NotNull
    private Double newValue;
    @NotNull
    private double oldValue;
}

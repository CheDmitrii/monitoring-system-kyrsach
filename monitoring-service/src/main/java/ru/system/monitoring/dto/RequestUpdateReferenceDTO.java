package ru.system.monitoring.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class RequestUpdateReferenceDTO {
    private UUID id;
    private double value;
}

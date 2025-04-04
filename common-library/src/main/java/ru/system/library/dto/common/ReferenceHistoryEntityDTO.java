package ru.system.library.dto.common;

import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.util.UUID;

@Data
@Builder
public class ReferenceHistoryEntityDTO {
    private UUID id;
    private Timestamp time;
    private double oldValue;
    private double newValue;
}

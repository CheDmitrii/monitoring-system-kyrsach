package ru.system.library.dto.common;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class SensorDTO {
    private UUID id;
    private String name;
    private String description;
    private String type;
    private ReferenceDTO reference;
    private List<JournalEntityDTO> journal;
}

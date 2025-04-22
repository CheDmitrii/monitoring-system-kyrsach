package ru.system.library.dto.common;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class ReferenceDTO {
    private UUID id;
    private UUID sensor_id;
    private String name;
    private Double value;
    private String type;
    List<ReferenceHistoryEntityDTO> history;
}

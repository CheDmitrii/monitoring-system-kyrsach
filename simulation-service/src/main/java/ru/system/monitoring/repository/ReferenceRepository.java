package ru.system.monitoring.repository;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ru.system.library.dto.common.ReferenceDTO;
import ru.system.library.dto.common.SensorDTO;
import ru.system.library.sql.queries.ReferenceSQLQueries;
import ru.system.library.sql.repository.repository.ReferenceRepositoryInterface;

import java.util.Map;
import java.util.UUID;

@Component
public class ReferenceRepository extends ReferenceRepositoryInterface {
    public ReferenceRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(namedParameterJdbcTemplate);
    }

    public UUID createReference(ReferenceDTO reference, SensorDTO sensorDTO, UUID sensor_id) {
        return namedParameterJdbcTemplate.queryForObject(
                ReferenceSQLQueries.CREATE_REFERENCE,
                Map.of(
                        "sensor_id", sensor_id,
                        "name", reference.getName(),
                        "value", reference.getValue(),
                        "type", sensorDTO.getType()
                ),
                UUID.class
        );
    }
}

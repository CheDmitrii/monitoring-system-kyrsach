package ru.system.monitoring.repository;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ru.system.library.dto.common.SensorDTO;
import ru.system.library.sql.queries.SensorSQLQueries;
import ru.system.library.sql.repository.SensorRepositoryInterface;

import java.util.Map;
import java.util.UUID;

@Component
public class SensorRepository extends SensorRepositoryInterface {

    public SensorRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(namedParameterJdbcTemplate);
    }

    public UUID createSensor(SensorDTO sensorDTO) {
        return namedParameterJdbcTemplate.queryForObject(
                SensorSQLQueries.CREATE_SENSOR,
                Map.of(
                        "name", sensorDTO.getName(),
                        "type", sensorDTO.getType(),
                        "description", sensorDTO.getDescription()
                ),
                UUID.class
        );
    }
}

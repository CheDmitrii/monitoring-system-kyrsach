package ru.system.monitoring.repository.repository;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ru.system.library.dto.common.SensorDTO;
import ru.system.library.sql.repository.SensorRepositoryInterface;
import ru.system.monitoring.repository.mapper.SensorMapper;
import ru.system.library.sql.queries.SensorSQLQueries;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class SensorRepository extends SensorRepositoryInterface {
    private final SensorMapper sensorMapper;

    public SensorRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                            SensorMapper sensorMapper) {
        super(namedParameterJdbcTemplate);
        this.sensorMapper = sensorMapper;
    }

    public SensorDTO getSensorInfo(UUID id) {
        return namedParameterJdbcTemplate.queryForObject(
                SensorSQLQueries.GET_SENSOR_BY_ID,
                Map.of("id", id),
                sensorMapper);
    }

    public List<SensorDTO> getAllSensors() {
        return namedParameterJdbcTemplate.query(
                SensorSQLQueries.GET_ALL_SENSORS,
                sensorMapper
        );
    }
}

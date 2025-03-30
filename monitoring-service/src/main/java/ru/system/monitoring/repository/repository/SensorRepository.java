package ru.system.monitoring.repository.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ru.system.library.dto.SensorDTO;
import ru.system.monitoring.repository.mapper.SensorMapper;
import ru.system.monitoring.repository.queries.SensorSQLQueries;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SensorRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SensorMapper sensorMapper;

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

    public boolean existsSensor(UUID id) {
        return Boolean.TRUE.equals(namedParameterJdbcTemplate.queryForObject(
                SensorSQLQueries.EXIST_SENSOR,
                Map.of("id", id),
                Boolean.class
        ));
    }

    public void createSensor(SensorDTO sensorDTO) {
        namedParameterJdbcTemplate.update(
                "INSERT INTO system.sensor VALUES(:id, :name, :type, :description)",
                Map.of(
                        "id", sensorDTO.getId(),
                        "name", sensorDTO.getName(),
                        "type", sensorDTO.getType(),
                        "description", sensorDTO.getDescription()
                )
        );
    }
}

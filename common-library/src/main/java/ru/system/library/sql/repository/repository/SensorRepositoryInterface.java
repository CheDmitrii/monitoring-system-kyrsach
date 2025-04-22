package ru.system.library.sql.repository.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.system.library.sql.queries.SensorSQLQueries;

import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
public abstract class SensorRepositoryInterface {

    protected final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public boolean existsSensor(UUID id) {
        return Boolean.TRUE.equals(namedParameterJdbcTemplate.queryForObject(
                SensorSQLQueries.EXIST_SENSOR,
                Map.of("id", id),
                Boolean.class
        ));
    }
}

package ru.system.monitoring.repository.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ru.system.monitoring.repository.queries.ReferenceSQLQueries;

import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ReferenceRepository {
    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public void changeValue(UUID id, double value) {
        namedParameterJdbcTemplate.update(
                ReferenceSQLQueries.UPDATE_VALUE,
                Map.of("id", id, "value", value));
    }

    public boolean existsReference(UUID id) {
        return Boolean.TRUE.equals(
                jdbcTemplate.queryForObject(
                        ReferenceSQLQueries.EXISTS_REFERENCE,
                        Map.of("id", id),
                        Boolean.class
                )
        );
    }
}

package ru.system.library.sql.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import ru.system.library.sql.queries.ReferenceSQLQueries;

import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
public abstract class ReferenceRepositoryInterface {
    protected final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public boolean existsReference(UUID id) {
        return Boolean.TRUE.equals(
                namedParameterJdbcTemplate.queryForObject(
                        ReferenceSQLQueries.EXISTS_REFERENCE,
                        Map.of("id", id),
                        Boolean.class
                )
        );
    }
}

package ru.system.monitoring.repository.repository;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;
import ru.system.library.dto.common.ReferenceDTO;
import ru.system.library.dto.common.ReferenceHistoryEntityDTO;
import ru.system.library.sql.queries.ReferenceSQLQueries;
import ru.system.library.sql.repository.repository.ReferenceRepositoryInterface;
import ru.system.monitoring.dto.RequestUpdateReferenceDTO;
import ru.system.library.sql.repository.mapper.ReferenceHistoryMapper;
import ru.system.library.sql.repository.mapper.ReferenceMapper;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
public class ReferenceRepository extends ReferenceRepositoryInterface {

    private final ReferenceMapper referenceMapper;
    private final ReferenceHistoryMapper referenceHistoryMapper;

    public ReferenceRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, ReferenceMapper referenceMapper, ReferenceHistoryMapper referenceHistoryMapper) {
        super(namedParameterJdbcTemplate);
        this.referenceMapper = referenceMapper;
        this.referenceHistoryMapper = referenceHistoryMapper;
    }

    public ReferenceDTO getReferenceById(UUID id) {
        return namedParameterJdbcTemplate.queryForObject(
                ReferenceSQLQueries.GET_REFERENCE_BY_ID,
                Map.of("id", id),
                referenceMapper);
    }

    public List<ReferenceDTO> getAllReferences() {
        return namedParameterJdbcTemplate.query(ReferenceSQLQueries.GET_ALL_REFERENCES, referenceMapper);
    }

    public List<ReferenceHistoryEntityDTO> getReferenceHistory(UUID id) {
        return namedParameterJdbcTemplate.query(
                ReferenceSQLQueries.GET_REFERENCE_HISTORY_BY_ID,
                Map.of("id", id),
                referenceHistoryMapper);
    }

    public List<ReferenceHistoryEntityDTO> getAllReferenceHistory() {
        return namedParameterJdbcTemplate.query(
                ReferenceSQLQueries.GET_ALL_REFERENCE_HISTORY,
                referenceHistoryMapper
        );
    }


    public void changeValue(RequestUpdateReferenceDTO updateReference, Timestamp time) {
        namedParameterJdbcTemplate.update(
                ReferenceSQLQueries.CREATE_REFERENCE_HISTORY,
                Map.of("id", updateReference.getId(),
                        "old_value", updateReference.getOldValue(),
                        "new_value", updateReference.getNewValue(),
                        "time", time)
        );
        namedParameterJdbcTemplate.update(
                ReferenceSQLQueries.UPDATE_VALUE,
                Map.of("id", updateReference.getId(), "value", updateReference.getNewValue()));
    }
}

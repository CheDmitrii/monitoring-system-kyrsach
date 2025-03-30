package ru.system.monitoring.repository.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import ru.system.library.dto.JournalEntityDTO;
import ru.system.library.dto.PayloadTypeDTO;
import ru.system.monitoring.repository.mapper.JournalEntityRowMapper;
import ru.system.monitoring.repository.queries.JournalSQLQueries;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JournalRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final JournalEntityRowMapper journalEntityRowMapper;

    public void writeJournal(JournalEntityDTO journalEntityDTO) {
        PayloadTypeDTO payload = journalEntityDTO.getPayload();
        namedParameterJdbcTemplate.update(
                JournalSQLQueries.WRITE_JOURNAL,
                Map.of(
                        "id_sensor", journalEntityDTO.getId(),
                        "type", payload.getType(),
                        "value", payload.getValue(),
                        "time", journalEntityDTO.getTime()
                )
        );
    }

    public List<JournalEntityDTO> getAllJournals(UUID id_sensor) {
        List<JournalEntityDTO> result = namedParameterJdbcTemplate.query(
                JournalSQLQueries.GET_JOURNALS_BY_ID,
                Map.of("id_sensor", id_sensor),
                journalEntityRowMapper
        );
        result.sort(Comparator.comparing(JournalEntityDTO::getTime));
        return result;
    }

    public List<JournalEntityDTO> getAllJournals() {
        return namedParameterJdbcTemplate.query(
                JournalSQLQueries.GET_ALL_JOURNALS,
                journalEntityRowMapper
        );
    }
}

package ru.system.monitoring.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.system.library.dto.JournalEntityDTO;
import ru.system.library.dto.PayloadTypeDTO;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.UUID;

@Component
public class JournalEntityRowMapper implements RowMapper<JournalEntityDTO> {
    @Override
    public JournalEntityDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return JournalEntityDTO.builder()
                .id(UUID.fromString(rs.getString("id_sensor")))
                .payload(PayloadTypeDTO.builder()
                        .type(rs.getString("type"))
                        .value(rs.getDouble("value"))
                        .build())
                .time(Timestamp.valueOf(rs.getString("time")))
                .build();
    }
}

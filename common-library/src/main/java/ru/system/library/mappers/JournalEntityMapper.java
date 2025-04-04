package ru.system.library.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.system.library.dto.common.JournalEntityDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.UUID;


public class JournalEntityMapper implements RowMapper<JournalEntityDTO> {
    @Override
    public JournalEntityDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return JournalEntityDTO.builder()
                .id(UUID.fromString(rs.getString("sensor_id")))
                .value(rs.getDouble("value"))
                .time(Timestamp.valueOf(rs.getString("time")))
                .build();
    }
}

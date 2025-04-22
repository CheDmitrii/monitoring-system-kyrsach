package ru.system.library.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.system.library.dto.common.JournalEntityDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.UUID;


public class JournalEntityMapper implements RowMapper<JournalEntityDTO> {
    private final Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

    @Override
    public JournalEntityDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return JournalEntityDTO.builder()
                .id(UUID.fromString(rs.getString("sensor_id")))
                .value(rs.getDouble("value"))
                .time((rs.getTimestamp("time", calendar)))
                .build();
    }
}

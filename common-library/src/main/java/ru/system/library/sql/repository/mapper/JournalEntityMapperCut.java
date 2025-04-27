package ru.system.library.sql.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.system.library.dto.common.JournalEntityDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.TimeZone;

public class JournalEntityMapperCut implements RowMapper<JournalEntityDTO> {

    private final Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

    @Override
    public JournalEntityDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return JournalEntityDTO.builder()
                .value(rs.getDouble("value"))
                .time((rs.getTimestamp("time", calendar)))
                .build();
    }
}

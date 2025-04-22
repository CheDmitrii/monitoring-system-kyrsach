package ru.system.library.sql.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.system.library.dto.common.ReferenceHistoryEntityDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.UUID;


public class ReferenceHistoryMapper implements RowMapper<ReferenceHistoryEntityDTO> {
    private final Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

    @Override
    public ReferenceHistoryEntityDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return ReferenceHistoryEntityDTO.builder()
                .id(UUID.fromString(rs.getString("id")))
                .time(rs.getTimestamp("time", calendar))
                .oldValue(rs.getDouble("old_value"))
                .newValue(rs.getDouble("new_value"))
                .build();
    }
}

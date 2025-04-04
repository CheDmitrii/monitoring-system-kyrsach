package ru.system.library.mappers;

import org.springframework.jdbc.core.RowMapper;
import ru.system.library.dto.common.ReferenceHistoryEntityDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.UUID;


public class ReferenceHistoryMapper implements RowMapper<ReferenceHistoryEntityDTO> {
    @Override
    public ReferenceHistoryEntityDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return ReferenceHistoryEntityDTO.builder()
                .id(UUID.fromString(rs.getString("id")))
                .time(Timestamp.valueOf(rs.getString("time")))
                .oldValue(rs.getDouble("old_value"))
                .newValue(rs.getDouble("new_value"))
                .build();
    }
}

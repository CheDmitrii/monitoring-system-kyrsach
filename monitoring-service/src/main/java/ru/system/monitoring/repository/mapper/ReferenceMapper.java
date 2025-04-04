package ru.system.monitoring.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.system.library.dto.common.ReferenceDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class ReferenceMapper implements RowMapper<ReferenceDTO> {
    @Override
    public ReferenceDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return ReferenceDTO.builder()
                .id(UUID.fromString(rs.getString("id")))
                .name(rs.getString("name"))
                .value(rs.getDouble("value"))
                .type(rs.getString("type"))
                .sensor_id(UUID.fromString(rs.getString("sensor_id")))
                .build();
    }
}

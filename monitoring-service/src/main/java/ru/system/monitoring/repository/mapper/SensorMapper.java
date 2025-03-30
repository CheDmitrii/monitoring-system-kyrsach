package ru.system.monitoring.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.system.library.dto.SensorDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class SensorMapper implements RowMapper<SensorDTO> {
    @Override
    public SensorDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        return SensorDTO.builder()
                .id(UUID.fromString(rs.getString("id")))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .referenceValue(rs.getDouble("value"))
                .type(rs.getString("type"))
                .build();
    }
}

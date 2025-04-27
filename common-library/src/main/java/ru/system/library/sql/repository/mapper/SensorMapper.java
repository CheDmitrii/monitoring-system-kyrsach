package ru.system.library.sql.repository.mapper;

import org.springframework.jdbc.core.RowMapper;
import ru.system.library.dto.common.ReferenceDTO;
import ru.system.library.dto.common.SensorDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SensorMapper implements RowMapper<SensorDTO> {
    @Override
    public SensorDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
        String referenceId = rs.getString("reference_id");
        return SensorDTO.builder()
                .id(UUID.fromString(rs.getString("id")))
                .name(rs.getString("name"))
                .description(rs.getString("description"))
                .reference(referenceId != null ?
                        ReferenceDTO.builder()
                                .id(UUID.fromString(referenceId))
                                .name(rs.getString("reference_name"))
                                .value(rs.getDouble("value"))
                                .build() : null)
                .type(rs.getString("type"))
                .build();
    }
}

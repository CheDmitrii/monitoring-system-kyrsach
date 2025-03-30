package ru.system.monitoring.config.db;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataBaseConfig {

    // TODO: add if not exists in migration in both place
    // TODO: implement sockets
    @Bean
    public NamedParameterJdbcTemplate getJDBCTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
}

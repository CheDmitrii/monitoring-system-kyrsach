package ru.system.library.config.db;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class DataBaseConfig {

    // TODO: add if not exists in migration in both place
    // todo: in diplomas implement security, UPC server, switch on JPA and maybe use rsockets
    @Bean
    @ConditionalOnBean(DataSource.class)
    public NamedParameterJdbcTemplate getJDBCTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
}

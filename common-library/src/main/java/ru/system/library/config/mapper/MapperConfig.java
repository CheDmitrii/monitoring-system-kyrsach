package ru.system.library.config.mapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.system.library.sql.repository.mapper.*;

@Configuration
public class MapperConfig {
    @Bean
    public JournalEntityMapper kafkaJournalEntityMapper() {
        return new JournalEntityMapper();
    }

    @Bean
    public ReferenceHistoryMapper referenceHistoryMapper() {
        return new ReferenceHistoryMapper();
    }

    @Bean
    public JournalEntityMapperCut journalEntityMapper() {
        return new JournalEntityMapperCut();
    }

    @Bean
    public SensorMapper sensorMapper() {
        return new SensorMapper();
    }

    @Bean
    public ReferenceMapper referenceMapper() {
        return new ReferenceMapper();
    }
}

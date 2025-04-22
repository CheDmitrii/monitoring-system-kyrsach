package ru.system.library.config.mapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.system.library.mappers.CutJournalEntityMapper;
import ru.system.library.mappers.JournalEntityMapper;
import ru.system.library.mappers.ReferenceHistoryMapper;

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
    public CutJournalEntityMapper journalEntityMapper() {
        return new CutJournalEntityMapper();
    }
}

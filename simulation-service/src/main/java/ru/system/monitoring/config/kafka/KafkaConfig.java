package ru.system.monitoring.config.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import ru.system.library.dto.common.JournalEntityDTO;

@Configuration
@EnableKafka
@RequiredArgsConstructor
public class KafkaConfig {
    private final KafkaProperties kafkaProperties;

    @Bean
    public ProducerFactory<String, JournalEntityDTO> producerFactory() {
        return new DefaultKafkaProducerFactory<>(kafkaProperties.buildProducerProperties());
    }

    @Bean
    public KafkaTemplate<String, JournalEntityDTO> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}

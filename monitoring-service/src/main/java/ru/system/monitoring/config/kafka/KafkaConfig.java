package ru.system.monitoring.config.kafka;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@EnableKafka
@EnableKafkaStreams
@RequiredArgsConstructor
public class KafkaConfig {

    @Value("${spring.kafka.topics.journal-topic}")
    private String JOURNAL_TOPIC_VALUE;


    @Bean
    public NewTopic retentionTopic1() {
        return TopicBuilder
                .name(JOURNAL_TOPIC_VALUE)
                .config(TopicConfig.RETENTION_MS_CONFIG, "3600000")
                .build();
    }
}

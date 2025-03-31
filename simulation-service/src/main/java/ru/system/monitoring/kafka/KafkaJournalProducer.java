package ru.system.monitoring.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.system.library.dto.common.JournalEntityDTO;

@Component
@RequiredArgsConstructor
public class KafkaJournalProducer {
    private final KafkaTemplate<String, JournalEntityDTO> kafkaTemplate;

    public void send(String topic, JournalEntityDTO journal) {
        kafkaTemplate.send(topic, journal.getId().toString(), journal);
    }
}

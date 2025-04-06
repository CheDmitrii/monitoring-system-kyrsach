package ru.system.monitoring.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import ru.system.library.dto.common.JournalEntityDTO;
import ru.system.monitoring.service.JournalService;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class JournalConsumer {

    @Value("${spring.kafka.topics.journal-topic}")
    private String JOURNAL_TOPIC_VALUE;
    private final JournalService journalService;
    private final SimpMessagingTemplate messagingTemplate;

    @Bean
    public KStream<String, JournalEntityDTO> monitoringStream(StreamsBuilder builder) {
        KStream<String, JournalEntityDTO> stream = builder.stream(
                JOURNAL_TOPIC_VALUE,
                Consumed.with(
                        Serdes.String(),
                        new JsonSerde<>(
                                new JsonSerializer<>(),
                                new JsonDeserializer<>(JournalEntityDTO.class)
                        )
                )
        );
        stream.foreach((k, v) -> {
            log.info("Consume journal {}", v);
            if (!journalService.isSensorExist(v.getId())) {
                log.error("Sensor with id {} not found:    value => {}, time => {}", v.getId(), v.getValue(), v.getTime());
                return;
            }
            journalService.saveJournal(v);
            log.info("Save journal {}", v);
            messagingTemplate.convertAndSend("/topic/journal", v);
            messagingTemplate.convertAndSend("/topic/journal" + v.getId(), v);
        });
        return stream;
    }
}

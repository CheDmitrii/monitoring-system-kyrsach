package ru.system.monitoring.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import ru.system.library.dto.JournalEntityDTO;
import ru.system.monitoring.service.JournalService;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class MonitoringConsumer {

    @Value("${spring.kafka.topics.monitoring-topic}")
    private String TOPIC_VALUE;
    private final JournalService journalService;
    private final SimpMessagingTemplate messagingTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper(); // don't need

    @Bean
    public KStream<String, JournalEntityDTO> monitoringStream(StreamsBuilder builder) {
        KStream<String, JournalEntityDTO> stream = builder.stream(
                "test2",
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
            journalService.saveJournal(v);
            log.info("Save journal {}", v);
            messagingTemplate.convertAndSend("/topic/journal", v);
            messagingTemplate.convertAndSend("/topic/journal" + v.getId(), v);
        });
        return stream;
    }
}

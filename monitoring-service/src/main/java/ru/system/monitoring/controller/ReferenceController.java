package ru.system.monitoring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.system.library.dto.common.ReferenceDTO;
import ru.system.monitoring.dto.RequestUpdateReferenceDTO;
import ru.system.monitoring.service.ReferenceService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/reference")
@RequiredArgsConstructor
public class ReferenceController {

    private final ReferenceService referenceService;
    private final SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/reference/update") // for this annotation doesn't work @RequestMapping (send on "/app" (from socket config) + "/reference/update")
    public void changeReference(RequestUpdateReferenceDTO update) {
        Timestamp time = Timestamp.valueOf(LocalDateTime.now());
        referenceService.saveChanges(update, time);
        messagingTemplate.convertAndSend("/topic/references", update);
        messagingTemplate.convertAndSend("/topic/references" + update.getId(), update);
    }

    @GetMapping("/history/all")
    public Flux<ReferenceDTO> getReferences() {
        return Flux.fromIterable(referenceService.getAllReferences());
    }

    @GetMapping("/history/{id:.+}")
    public Mono<ReferenceDTO> getReferenceById(@PathVariable("id") UUID id) {
        return Mono.just(referenceService.getReference(id));
    }
}

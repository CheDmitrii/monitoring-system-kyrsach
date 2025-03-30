package ru.system.monitoring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.system.monitoring.service.ReferenceService;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/reference")
@RequiredArgsConstructor
public class ReferenceController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ReferenceService referenceService;

    @MessageMapping("/references")
    public ResponseEntity<Object> changeReference(UUID id, double newValue) {
        referenceService.saveChanges(id, newValue); // todo maybe switch on Mono
        messagingTemplate.convertAndSend("/topic/reference", Map.of(id, newValue));
        messagingTemplate.convertAndSend("/topic/reference/" + id, newValue);
        return ResponseEntity.ok().build();
    }
}

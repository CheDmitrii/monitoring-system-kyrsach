package ru.system.monitoring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.system.monitoring.service.ReferenceService;

import java.util.UUID;

@RestController
@RequestMapping("/reference")
@RequiredArgsConstructor
public class ReferenceController {

    private final ReferenceService referenceService;

    @PutMapping("/update")
    public Mono<ResponseEntity<String>> getReference(@RequestBody Pair<UUID, Double> update) {
        referenceService.changeValue(update.getFirst(), update.getSecond());
        return Mono.just(ResponseEntity.ok(update.getFirst().toString()));
    }
}

package ru.system.monitoring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.system.monitoring.dto.RequestUpdateReferenceDTO;
import ru.system.monitoring.service.ReferenceService;

@RestController
@RequestMapping("/reference")
@RequiredArgsConstructor
public class ReferenceController {

    private final ReferenceService referenceService;

    @PatchMapping("/update")
    public Mono<ResponseEntity<Void>> changeReference(@RequestBody RequestUpdateReferenceDTO update) {
        referenceService.saveChanges(update.getId(), update.getValue());
        return Mono.just(ResponseEntity.ok().build());
    }
}

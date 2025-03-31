package ru.system.monitoring.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.system.library.dto.common.JournalEntityDTO;
import ru.system.monitoring.service.JournalService;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/simulation")
@RequiredArgsConstructor
public class SimulationController {

    protected final JournalService journalService;

    @PostMapping("/send-parameter/{id:.+}")
    public Mono<ResponseEntity<Void>> sendParameter(@PathVariable("id") UUID id, @RequestBody double value) {
        journalService.sendJournal(JournalEntityDTO.builder()
                .id(id)
                .value(value)
                .time(Timestamp.valueOf(LocalDateTime.now()))
                .build());
        return Mono.just(ResponseEntity.ok().build());
    }
}

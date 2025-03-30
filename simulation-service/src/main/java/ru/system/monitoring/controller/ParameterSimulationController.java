package ru.system.monitoring.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/generate")
public class ParameterSimulationController {

    @PostMapping("/send-parameter")
    public ResponseEntity<Void> sendParameter() {
        return ResponseEntity.ok().build();
    }
}

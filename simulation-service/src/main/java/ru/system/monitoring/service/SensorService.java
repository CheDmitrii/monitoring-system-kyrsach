package ru.system.monitoring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.system.library.dto.common.SensorDTO;
import ru.system.library.dto.request.CreateSensorDTO;
import ru.system.monitoring.repository.SensorRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SensorService {
    private final SensorRepository sensorRepository;

    public UUID createSensor(CreateSensorDTO createSensor) {
        return sensorRepository.createSensor(SensorDTO.builder()
                .name(createSensor.getName())
                .referenceValue(createSensor.getReferenceValue())
                .type(createSensor.getType())
                .description(createSensor.getDescription())
                .build());
    }
}

package ru.system.monitoring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.system.library.dto.common.SensorDTO;
import ru.system.monitoring.repository.ReferenceRepository;
import ru.system.monitoring.repository.SensorRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SensorService {
    private final SensorRepository sensorRepository;
    private final ReferenceRepository referenceRepository;

    public UUID createSensor(SensorDTO createSensor) {
        UUID sensorId = sensorRepository.createSensor(createSensor);
        if (createSensor.getReference() != null) {
            referenceRepository.createReference(createSensor.getReference(), createSensor, sensorId);
        }
        return sensorId;
    }
}

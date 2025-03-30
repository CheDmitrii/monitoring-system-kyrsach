package ru.system.monitoring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.system.library.dto.JournalEntityDTO;
import ru.system.library.dto.SensorDTO;
import ru.system.monitoring.repository.repository.SensorRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SensorService {

    private final SensorRepository sensorRepository;
    private final JournalService journalService;

    public SensorDTO getSensorById(UUID id) {
        if (!sensorRepository.existsSensor(id)) {
            // todo throw exception
        }
        SensorDTO sensorInfo = sensorRepository.getSensorInfo(id);
        sensorInfo.setJournal(journalService.getSensorData(id));
        return sensorInfo;
    }

    public List<SensorDTO> getAllSensors() {
        List<SensorDTO> allSensors = sensorRepository.getAllSensors();
        Map<UUID, List<JournalEntityDTO>> collectJournal = journalService.getAllSensorsData().stream().collect(Collectors.groupingBy(JournalEntityDTO::getId));
        allSensors.forEach(v -> v.setJournal(collectJournal.get(v.getId())));
        return allSensors;
    }

    public void saveSensor() {
        sensorRepository.createSensor(SensorDTO
                .builder()
                .id(UUID.randomUUID())
                .type("test")
                .name("ssss")
                .description("ddd")
                .build());
    }
}

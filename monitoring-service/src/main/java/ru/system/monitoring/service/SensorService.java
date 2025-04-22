package ru.system.monitoring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.system.library.dto.common.JournalEntityDTO;
import ru.system.library.dto.common.SensorDTO;
import ru.system.library.exception.HttpResponseEntityException;
import ru.system.monitoring.repository.repository.SensorRepository;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SensorService {

    private final SensorRepository sensorRepository;
    private final JournalService journalService;

    public SensorDTO getSensorById(UUID id) {
        if (!sensorRepository.existsSensor(id)) {
            throw new HttpResponseEntityException(HttpStatus.NOT_FOUND, "Sensor with this id {%s} doesn't exist".formatted(id));
        }
        SensorDTO sensorInfo = sensorRepository.getSensorInfo(id);
        sensorInfo.setJournal(journalService.getSensorJournal(id));
        return sensorInfo;
    }

    public List<SensorDTO> getAllSensors() {
        List<SensorDTO> allSensors = sensorRepository.getAllSensors();
        Map<UUID, List<JournalEntityDTO>> collectJournal = journalService.getAllSensorsData().stream()
                .collect(
                        Collectors.groupingBy(
                                JournalEntityDTO::getId,
                                TreeMap::new,
                                Collectors.mapping(
                                        v -> {
                                            v.setId(null);
                                            return v;
                                            }, // JournalEntityDTO.builder().value(v.getValue()).time(v.getTime()).build()
                                        Collectors.toList()
                                )
                        )
                );
        allSensors.forEach(v -> v.setJournal(collectJournal.get(v.getId())));
        return allSensors;
    }
}

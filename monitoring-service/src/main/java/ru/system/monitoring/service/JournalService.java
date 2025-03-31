package ru.system.monitoring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import ru.system.library.dto.common.JournalEntityDTO;
import ru.system.monitoring.repository.repository.JournalRepository;
import ru.system.monitoring.repository.repository.SensorRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JournalService {
    private final JournalRepository journalRepository;
    private final SensorRepository sensorRepository; // todo implement checking sensor

    @Async
    public void saveJournal(JournalEntityDTO journalEntityDTO) {
        journalRepository.writeJournal(journalEntityDTO);
    }


    public List<JournalEntityDTO> getSensorJournal(UUID sensor_id) {
        return journalRepository.getAllJournals(sensor_id);
    }

    public List<JournalEntityDTO> getAllSensorsData() {
        return journalRepository.getAllJournals();
    }
}

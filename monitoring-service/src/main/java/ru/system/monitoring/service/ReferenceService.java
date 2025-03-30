package ru.system.monitoring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.system.monitoring.repository.repository.ReferenceRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReferenceService {
    private final ReferenceRepository referenceRepository;


    public void saveChanges (UUID id, double newValue) {
        if (!referenceRepository.existsReference(id)) {
            // todo throw exception
        }
        referenceRepository.changeValue(id, newValue);
    }
}

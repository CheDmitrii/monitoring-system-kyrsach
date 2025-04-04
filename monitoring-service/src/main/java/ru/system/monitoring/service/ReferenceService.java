package ru.system.monitoring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.system.library.exception.HttpResponseEntityException;
import ru.system.monitoring.repository.repository.ReferenceRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReferenceService {
    private final ReferenceRepository referenceRepository;


    public void saveChanges (UUID id, double newValue) {
        if (!referenceRepository.existsReference(id)) {
            throw new HttpResponseEntityException(HttpStatus.NOT_FOUND, "Reference with this id {%s} doesn't exist".formatted(id));
        }
        referenceRepository.changeValue(id, newValue);
    }
}

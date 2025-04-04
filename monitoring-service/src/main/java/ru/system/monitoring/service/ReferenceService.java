package ru.system.monitoring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import ru.system.library.dto.common.ReferenceDTO;
import ru.system.library.dto.common.ReferenceHistoryEntityDTO;
import ru.system.library.exception.HttpResponseEntityException;
import ru.system.monitoring.dto.RequestUpdateReferenceDTO;
import ru.system.monitoring.repository.repository.ReferenceRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReferenceService {
    private final ReferenceRepository referenceRepository;


    public void saveChanges (RequestUpdateReferenceDTO updateReference, Timestamp time) {
        if (!referenceRepository.existsReference(updateReference.getId())) {
            throw new HttpResponseEntityException(HttpStatus.NOT_FOUND, "Reference with this id {%s} doesn't exist".formatted(updateReference.getId()));
        }
        referenceRepository.changeValue(updateReference, time);
    }

    public ReferenceDTO getReference(UUID id) {
        if (!referenceRepository.existsReference(id)) {
            throw new HttpResponseEntityException(HttpStatus.NOT_FOUND, "Reference with this id {%s} doesn't exist".formatted(id));
        }
        ReferenceDTO reference = referenceRepository.getReferenceById(id);
        reference.setHistory(referenceRepository.getReferenceHistory(id));
        return reference;
    }

    public List<ReferenceDTO> getAllReferences() {
        List<ReferenceDTO> references = referenceRepository.getAllReferences();
        Map<UUID, List<ReferenceHistoryEntityDTO>> groupedHistory = referenceRepository.getAllReferenceHistory().stream().collect(Collectors.groupingBy(ReferenceHistoryEntityDTO::getId));
        references.forEach(referenceDTO -> referenceDTO.setHistory(groupedHistory.get(referenceDTO.getId())));
        return references;
    }
}

package kz.talimger.service;

import kz.talimger.dto.specialization.CreateSpecializationDto;
import kz.talimger.dto.specialization.SpecializationDto;
import kz.talimger.model.Specialization;

import java.util.List;
import java.util.UUID;

public interface SpecializationService {
    List<SpecializationDto> findAll();

    SpecializationDto findById(UUID id);

    SpecializationDto createSpecialization(CreateSpecializationDto dto);

    SpecializationDto editSpecialization(SpecializationDto dto);

    void deleteSpecialization(UUID id);

    Specialization find(UUID id);
}

package kz.talimger.service.impl;

import kz.talimger.dto.specialization.CreateSpecializationDto;
import kz.talimger.dto.specialization.SpecializationDto;
import kz.talimger.exception.KazNpuException;
import kz.talimger.mapper.SpecializationMapper;
import kz.talimger.model.Specialization;
import kz.talimger.repository.SpecializationRepository;
import kz.talimger.service.SpecializationService;
import kz.talimger.util.ErrorCodeConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SpecializationServiceImpl implements SpecializationService {

    private final SpecializationRepository specializationRepository;
    private final SpecializationMapper specializationMapper;

    @Override
    public List<SpecializationDto> findAll() {
        return specializationRepository.findAll().stream()
                .map(specializationMapper::toDto)
                .toList();
    }

    @Override
    public SpecializationDto findById(UUID id) {
        return specializationMapper.toDto(find(id));
    }

    @Override
    public SpecializationDto createSpecialization(CreateSpecializationDto dto) {
        Specialization specialization = specializationRepository.save(specializationMapper.toEntity(dto));
        return specializationMapper.toDto(specialization);
    }

    @Override
    public SpecializationDto editSpecialization(SpecializationDto dto) {
        Specialization specialization = find(dto.getId());
        specialization.setName(dto.getName());
        return specializationMapper.toDto(specializationRepository.save(specialization));
    }

    @Override
    public void deleteSpecialization(UUID id) {
        Specialization specialization = find(id);
        specializationRepository.delete(specialization);
    }

    @Override
    public Specialization find(UUID id) {
        return specializationRepository.findById(id)
                .orElseThrow(() -> new KazNpuException(HttpStatus.NOT_FOUND,
                        ErrorCodeConstant.SPECIALIZATION_NOT_FOUND,
                        "message.error.specialization-not-found"));
    }
}

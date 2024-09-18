package kz.talimger.service.impl;

import jakarta.transaction.Transactional;
import kz.talimger.dto.institution.InstitutionDTO;
import kz.talimger.enums.InstitutionEnum;
import kz.talimger.model.Address;
import kz.talimger.model.Kindergarten;
import kz.talimger.model.Point;
import kz.talimger.model.Rubric;
import kz.talimger.repository.KindergartenRepository;
import kz.talimger.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class KindergartenServiceImpl implements KindergartenService {

    private final KindergartenRepository kindergartenRepository;
    private final AddressService addressService;
    private final PointService pointService;
    private final AdmDivService admDivService;
    private final RubricService rubricService;

    @Transactional
    public void processInstitutions(List<InstitutionDTO> institutionDTOs) {
        if (institutionDTOs == null || institutionDTOs.isEmpty()) {
            throw new IllegalArgumentException("Institution list cannot be null or empty");
        }

        for (InstitutionDTO dto : institutionDTOs) {
            if (Objects.nonNull(dto.getAddress()) && Objects.nonNull(dto.getAddress().getBuildingId())) {
                List<Kindergarten> existingKindergarten = kindergartenRepository.findAllByAddress_BuildingId(dto.getAddress().getBuildingId());
                if (existingKindergarten.isEmpty()) {
                    Kindergarten kindergarten = mapToEntity(dto);
                    kindergartenRepository.save(kindergarten);
                }
            }
        }
    }

    private Kindergarten mapToEntity(InstitutionDTO dto) {
        Kindergarten kindergarten = new Kindergarten();
        kindergarten.setName(dto.getName());
        kindergarten.setLegalName(dto.getOrg().getName());
        if (dto.getNameEx() != null) {
            kindergarten.setShortName(dto.getNameEx().getShortName());
        }

        // Map Address
        Address address = addressService.processAddress(dto);
        kindergarten.setAddress(address);

        // Map Point
        Point point = pointService.processPoint(dto);
        kindergarten.setPoint(point);

        // Process Administrative Divisions
        admDivService.processAdministrativeDivisions(dto, address, kindergarten);

        // Map Rubrics
        List<Rubric> rubrics = rubricService.processRubricsMigration(dto, InstitutionEnum.KINDERGARTEN);
        kindergarten.setRubrics(rubrics);

        return kindergarten;
    }
}

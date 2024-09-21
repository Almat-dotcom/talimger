package kz.talimger.service.impl;

import jakarta.transaction.Transactional;
import kz.talimger.dto.common.PageDto;
import kz.talimger.dto.institution.InstitutionDTO;
import kz.talimger.dto.kindergarten.KindergartenSearchDto;
import kz.talimger.dto.kindergarten.KindergartenViewDto;
import kz.talimger.enums.InstitutionEnum;
import kz.talimger.mapper.KindergartenMapper;
import kz.talimger.model.Address;
import kz.talimger.model.Kindergarten;
import kz.talimger.model.Point;
import kz.talimger.model.Rubric;
import kz.talimger.repository.KindergartenRepository;
import kz.talimger.service.*;
import kz.talimger.specification.KindergartenSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class KindergartenServiceImpl implements KindergartenService {

    private final KindergartenRepository kindergartenRepository;
    private final AddressService addressService;
    private final PointService pointService;
    private final AdmDivService admDivService;
    private final RubricService rubricService;
    private final KindergartenMapper kindergartenMapper;

    @Override
    public void processInstitutions(List<InstitutionDTO> institutionDTOs) {
        if (Objects.isNull(institutionDTOs) || institutionDTOs.isEmpty()) {
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

    @Override
    public PageDto<KindergartenViewDto> getPageView(KindergartenSearchDto searchDto, Pageable pageable) {
        Specification<Kindergarten> kindergartenSpecification = KindergartenSpecification.query(searchDto);
        return new PageDto<>(
                kindergartenRepository.findAll(kindergartenSpecification, pageable)
                        .map(kindergartenMapper::toKindergartenViewDto)
        );
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

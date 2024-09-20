package kz.talimger.service.impl;

import jakarta.transaction.Transactional;
import kz.talimger.dto.institution.InstitutionDTO;
import kz.talimger.dto.school.SchoolSearchDto;
import kz.talimger.dto.school.SchoolViewDto;
import kz.talimger.enums.InstitutionEnum;
import kz.talimger.mapper.SchoolMapper;
import kz.talimger.model.Address;
import kz.talimger.model.Point;
import kz.talimger.model.Rubric;
import kz.talimger.model.School;
import kz.talimger.repository.SchoolRepository;
import kz.talimger.service.*;
import kz.talimger.specification.SchoolSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
@RequiredArgsConstructor
public class SchoolServiceImpl implements SchoolService {

    private final SchoolRepository schoolRepository;
    private final AddressService addressService;
    private final PointService pointService;
    private final AdmDivService admDivService;
    private final RubricService rubricService;
    private final SchoolMapper schoolMapper;

    @Override
    public void processInstitutions(List<InstitutionDTO> institutionDTOs) {
        if (Objects.isNull(institutionDTOs) || institutionDTOs.isEmpty()) {
            throw new IllegalArgumentException("Institution list cannot be null or empty");
        }

        for (InstitutionDTO dto : institutionDTOs) {
            if (Objects.nonNull(dto.getAddress()) && Objects.nonNull(dto.getAddress().getBuildingId())) {
                List<School> existingSchool = schoolRepository.findAllByAddress_BuildingId(dto.getAddress().getBuildingId());
                if (existingSchool.isEmpty()) {
                    School school = mapToEntity(dto);
                    schoolRepository.save(school);
                }
            }
        }
    }

    @Override
    public Page<SchoolViewDto> getPageView(SchoolSearchDto searchDto, Pageable pageable) {
        Specification<School> schoolSpecification = SchoolSpecification.query(searchDto);
        return schoolRepository.findAll(schoolSpecification, pageable)
                .map(schoolMapper::toSchoolViewDto);
    }

    private School mapToEntity(InstitutionDTO dto) {
        School school = new School();
        school.setName(dto.getName());
        school.setLegalName(dto.getOrg().getName());
        if (Objects.nonNull(dto.getNameEx())) {
            school.setShortName(dto.getNameEx().getShortName());
        }

        // Map Address
        Address address = addressService.processAddress(dto);
        school.setAddress(address);

        // Map Point
        Point point = pointService.processPoint(dto);
        school.setPoint(point);

        // Process Administrative Divisions
        admDivService.processAdministrativeDivisions(dto, address, school);

        // Map Rubrics
        List<Rubric> rubrics = rubricService.processRubricsMigration(dto, InstitutionEnum.SCHOOL);
        school.setRubrics(rubrics);

        return school;
    }
}

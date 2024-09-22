package kz.talimger.service.impl;

import jakarta.transaction.Transactional;
import kz.talimger.dto.common.PageDto;
import kz.talimger.dto.institution.InstitutionDTO;
import kz.talimger.dto.school.SchoolSearchDto;
import kz.talimger.dto.school.SchoolViewDto;
import kz.talimger.enums.InstitutionEnum;
import kz.talimger.exception.KazNpuException;
import kz.talimger.mapper.SchoolMapper;
import kz.talimger.model.Address;
import kz.talimger.model.Point;
import kz.talimger.model.Rubric;
import kz.talimger.model.School;
import kz.talimger.repository.SchoolRepository;
import kz.talimger.service.*;
import kz.talimger.specification.SchoolSpecification;
import kz.talimger.strategy.impl.SchoolQRCodeStrategy;
import kz.talimger.util.ErrorCodeConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class SchoolServiceImpl implements SchoolService, SchoolQrCodeService {

    private final SchoolRepository schoolRepository;
    private final AddressService addressService;
    private final PointService pointService;
    private final AdmDivService admDivService;
    private final RubricService rubricService;
    private final SchoolMapper schoolMapper;
    private final SchoolQRCodeStrategy qrCodeStrategy;

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
    public PageDto<SchoolViewDto> getPageView(SchoolSearchDto searchDto, Pageable pageable) {
        Specification<School> schoolSpecification = SchoolSpecification.query(searchDto);
        return new PageDto<>(
                schoolRepository.findAll(schoolSpecification, pageable)
                        .map(schoolMapper::toSchoolViewDto));
    }

    @Override
    public SchoolViewDto generateAndSaveQRCode(UUID schoolId) {
        return schoolMapper.toSchoolViewDto(qrCodeStrategy.generateAndSaveQRCode(schoolId));
    }

    @Override
    public byte[] generatePDFWithQRCode(UUID schoolId) {
        return qrCodeStrategy.generatePDFWithQRCode(schoolId);
    }

    @Override
    public SchoolViewDto deleteQrCode(UUID id) {
        return schoolMapper.toSchoolViewDto(qrCodeStrategy.deleteQrCode(id));
    }

    @Override
    public School find(UUID id) {
        return schoolRepository.findById(id)
                .orElseThrow(() -> new KazNpuException(
                        HttpStatus.FORBIDDEN,
                        ErrorCodeConstant.SCHOOL_NOT_FOUND,
                        "message.error.school-not-found"));
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

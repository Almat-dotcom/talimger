package kz.talimger.service;

import kz.talimger.dto.common.PageDto;
import kz.talimger.dto.institution.InstitutionDTO;
import kz.talimger.dto.school.SchoolSearchDto;
import kz.talimger.dto.school.SchoolViewDto;
import kz.talimger.model.School;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface SchoolService {
    void processInstitutions(List<InstitutionDTO> institutionDTOs);

    PageDto<SchoolViewDto> getPageView(SchoolSearchDto searchDto, Pageable pageable);

    School find(UUID id);
}

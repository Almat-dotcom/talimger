package kz.talimger.service;

import kz.talimger.dto.common.PageDto;
import kz.talimger.dto.institution.InstitutionDTO;
import kz.talimger.dto.kindergarten.KindergartenSearchDto;
import kz.talimger.dto.kindergarten.KindergartenViewDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface KindergartenService {
    void processInstitutions(List<InstitutionDTO> institutionDTOs);
    PageDto<KindergartenViewDto> getPageView(KindergartenSearchDto searchDto, Pageable pageable);
}

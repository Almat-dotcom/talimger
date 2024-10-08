package kz.talimger.service;

import kz.talimger.dto.common.PageDto;
import kz.talimger.dto.institution.InstitutionDTO;
import kz.talimger.dto.region.RegionViewDto;
import kz.talimger.model.Country;
import kz.talimger.model.Region;
import org.springframework.data.domain.Pageable;

public interface RegionService {
    PageDto<RegionViewDto> getPageView(Pageable pageable);

    Region findOrCreateMigration(InstitutionDTO.AdmDivDTO admDivDTO, Country country);
}

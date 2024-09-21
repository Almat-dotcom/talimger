package kz.talimger.service;

import kz.talimger.dto.common.PageDto;
import kz.talimger.dto.districtArea.DistrictAreaSearchDto;
import kz.talimger.dto.districtArea.DistrictAreaViewDto;
import kz.talimger.dto.institution.InstitutionDTO;
import kz.talimger.model.DistrictArea;
import kz.talimger.model.Region;
import org.springframework.data.domain.Pageable;

public interface DistrictAreaService {
    PageDto<DistrictAreaViewDto> getPageView(DistrictAreaSearchDto dto, Pageable pageable);

    DistrictArea findOrCreateMigration(InstitutionDTO.AdmDivDTO admDivDTO, Region region);
}

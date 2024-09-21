package kz.talimger.service;

import kz.talimger.dto.common.PageDto;
import kz.talimger.dto.institution.InstitutionDTO;
import kz.talimger.dto.settlement.SettlementSearchDto;
import kz.talimger.dto.settlement.SettlementViewDto;
import kz.talimger.model.Region;
import kz.talimger.model.Settlement;
import org.springframework.data.domain.Pageable;

public interface SettlementService {
    PageDto<SettlementViewDto> getPageView(SettlementSearchDto dto, Pageable pageable);

    Settlement findOrCreateMigration(InstitutionDTO.AdmDivDTO admDivDTO, Region region);
}

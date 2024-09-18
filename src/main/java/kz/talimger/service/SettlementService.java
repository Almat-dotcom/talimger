package kz.talimger.service;

import kz.talimger.dto.institution.InstitutionDTO;
import kz.talimger.model.Region;
import kz.talimger.model.Settlement;

public interface SettlementService {
    Settlement findOrCreateMigration(InstitutionDTO.AdmDivDTO admDivDTO, Region region);
}

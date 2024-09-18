package kz.talimger.service;

import kz.talimger.dto.institution.InstitutionDTO;
import kz.talimger.model.Country;
import kz.talimger.model.Region;

public interface RegionService {
    Region findOrCreateMigration(InstitutionDTO.AdmDivDTO admDivDTO, Country country);
}

package kz.talimger.service;

import kz.talimger.dto.institution.InstitutionDTO;
import kz.talimger.model.Country;

public interface CountryService {
    Country findOrCreateMigration(InstitutionDTO.AdmDivDTO admDivDTO);
}

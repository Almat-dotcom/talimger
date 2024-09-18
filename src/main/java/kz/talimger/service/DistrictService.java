package kz.talimger.service;

import kz.talimger.dto.institution.InstitutionDTO;
import kz.talimger.model.City;
import kz.talimger.model.Country;
import kz.talimger.model.District;

public interface DistrictService {
    District findOrCreateMigration(InstitutionDTO.AdmDivDTO admDivDTO, Country country, City city);
}

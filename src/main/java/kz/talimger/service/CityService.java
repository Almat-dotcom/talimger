package kz.talimger.service;

import kz.talimger.dto.institution.InstitutionDTO;
import kz.talimger.model.City;
import kz.talimger.model.Region;

public interface CityService {

    City findOrCreateMigration(InstitutionDTO.AdmDivDTO admDivDTO, Region region);
}

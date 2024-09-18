package kz.talimger.service;

import kz.talimger.dto.institution.InstitutionDTO;
import kz.talimger.model.DistrictArea;
import kz.talimger.model.Region;

public interface DistrictAreaService {
    DistrictArea findOrCreateMigration(InstitutionDTO.AdmDivDTO admDivDTO, Region region);
}

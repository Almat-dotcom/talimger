package kz.talimger.service;

import kz.talimger.dto.institution.InstitutionDTO;
import kz.talimger.model.Address;
import kz.talimger.model.Institution;

public interface AdmDivService {
    void processAdministrativeDivisions(InstitutionDTO dto, Address address, Institution institution);
}

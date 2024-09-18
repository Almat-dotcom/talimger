package kz.talimger.service;

import kz.talimger.dto.institution.InstitutionDTO;

import java.util.List;

public interface SchoolService {
    void processInstitutions(List<InstitutionDTO> institutionDTOs);
}

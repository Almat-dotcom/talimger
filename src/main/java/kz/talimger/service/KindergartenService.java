package kz.talimger.service;

import kz.talimger.dto.institution.InstitutionDTO;

import java.util.List;

public interface KindergartenService {
    void processInstitutions(List<InstitutionDTO> institutionDTOs);
}

package kz.talimger.service;

import kz.talimger.dto.institution.InstitutionDTO;
import kz.talimger.model.Address;

public interface AddressService {
    Address processAddress(InstitutionDTO dto);
}

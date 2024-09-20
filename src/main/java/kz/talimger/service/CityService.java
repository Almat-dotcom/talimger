package kz.talimger.service;

import kz.talimger.dto.city.CitySearchDto;
import kz.talimger.dto.city.CityViewDto;
import kz.talimger.dto.institution.InstitutionDTO;
import kz.talimger.model.City;
import kz.talimger.model.Region;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CityService {
    Page<CityViewDto> getPageView(CitySearchDto dto, Pageable pageable);

    City findOrCreateMigration(InstitutionDTO.AdmDivDTO admDivDTO, Region region);
}

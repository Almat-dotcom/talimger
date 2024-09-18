package kz.talimger.service.impl;

import kz.talimger.dto.institution.InstitutionDTO;
import kz.talimger.model.City;
import kz.talimger.model.Country;
import kz.talimger.model.District;
import kz.talimger.repository.DistrictRepository;
import kz.talimger.service.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;

    public District findOrCreateMigration(InstitutionDTO.AdmDivDTO admDivDTO, Country country, City city) {
        if (Objects.isNull(admDivDTO) || Objects.isNull(country) || Objects.isNull(city)) {
            return null;
        }

        String id = admDivDTO.getId();
        if (Objects.isNull(id)) {
            return null;
        }

        return districtRepository.findByLocationId(Long.valueOf(id))
                .orElseGet(() -> {
                    District district = new District();
                    district.setLocationId(Long.valueOf(id));
                    district.setName(admDivDTO.getName());
                    district.setCountry(country);
                    district.setCity(city);
                    return districtRepository.save(district);
                });
    }
}

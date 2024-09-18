package kz.talimger.service.impl;

import kz.talimger.dto.institution.InstitutionDTO;
import kz.talimger.model.Country;
import kz.talimger.repository.CountryRepository;
import kz.talimger.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public Country findOrCreateMigration(InstitutionDTO.AdmDivDTO admDivDTO) {
        if (Objects.isNull(admDivDTO)) {
            return null;
        }

        String id = admDivDTO.getId();
        if (Objects.isNull(id)) {
            return null;
        }

        return countryRepository.findByLocationId(Long.valueOf(id))
                .orElseGet(() -> {
                    Country country = new Country();
                    country.setLocationId(Long.valueOf(id));
                    country.setName(admDivDTO.getName());
                    return countryRepository.save(country);
                });
    }
}

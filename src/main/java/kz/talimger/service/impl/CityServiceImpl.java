package kz.talimger.service.impl;

import kz.talimger.dto.city.CitySearchDto;
import kz.talimger.dto.city.CityViewDto;
import kz.talimger.dto.common.PageDto;
import kz.talimger.dto.institution.InstitutionDTO;
import kz.talimger.mapper.CityMapper;
import kz.talimger.model.City;
import kz.talimger.model.Region;
import kz.talimger.repository.CityRepository;
import kz.talimger.service.CityService;
import kz.talimger.specification.CitySpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final CityMapper cityMapper;

    @Override
    public PageDto<CityViewDto> getPageView(CitySearchDto dto, Pageable pageable) {
        Specification<City> citySpecification = CitySpecification.query(dto);
        return new PageDto<>(
                cityRepository.findAll(citySpecification, pageable)
                        .map(cityMapper::toCityViewDto));
    }

    @Override
    public City findOrCreateMigration(InstitutionDTO.AdmDivDTO admDivDTO, Region region) {
        if (Objects.isNull(admDivDTO) || Objects.isNull(region)) {
            return null;
        }

        String id = admDivDTO.getId();
        if (Objects.isNull(id)) {
            return null;
        }

        return cityRepository.findByLocationId(Long.valueOf(id))
                .orElseGet(() -> {
                    City city = new City();
                    city.setLocationId(Long.valueOf(id));
                    city.setName(admDivDTO.getName());
                    city.setRegion(region);
                    city.setIsRegionCenter(admDivDTO.isDefault());
                    return cityRepository.save(city);
                });
    }
}

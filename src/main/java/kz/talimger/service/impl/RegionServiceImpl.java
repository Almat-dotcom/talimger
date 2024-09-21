package kz.talimger.service.impl;

import kz.talimger.dto.common.PageDto;
import kz.talimger.dto.institution.InstitutionDTO;
import kz.talimger.dto.region.RegionViewDto;
import kz.talimger.mapper.RegionMapper;
import kz.talimger.model.Country;
import kz.talimger.model.Region;
import kz.talimger.repository.RegionRepository;
import kz.talimger.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;
    private final RegionMapper regionMapper;

    @Override
    public PageDto<RegionViewDto> getPageView(Pageable pageable) {
        return new PageDto<>(
                regionRepository.findAll(pageable)
                        .map(regionMapper::toRegionViewDto));
    }

    public Region findOrCreateMigration(InstitutionDTO.AdmDivDTO admDivDTO, Country country) {
        if (Objects.isNull(admDivDTO) || Objects.isNull(country)) {
            return null;
        }

        String id = admDivDTO.getId();
        if (Objects.isNull(id)) {
            return null;
        }

        return regionRepository.findByLocationId(Long.valueOf(id))
                .orElseGet(() -> {
                    Region region = new Region();
                    region.setLocationId(Long.valueOf(id));
                    region.setName(admDivDTO.getName());
                    region.setCountry(country);
                    return regionRepository.save(region);
                });
    }
}

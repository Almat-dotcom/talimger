package kz.talimger.service.impl;

import kz.talimger.dto.common.PageDto;
import kz.talimger.dto.districtArea.DistrictAreaSearchDto;
import kz.talimger.dto.districtArea.DistrictAreaViewDto;
import kz.talimger.dto.institution.InstitutionDTO;
import kz.talimger.mapper.DistrictAreaMapper;
import kz.talimger.model.DistrictArea;
import kz.talimger.model.Region;
import kz.talimger.repository.DistrictAreaRepository;
import kz.talimger.service.DistrictAreaService;
import kz.talimger.specification.DistrictAreaSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DistrictAreaServiceImpl implements DistrictAreaService {

    private final DistrictAreaRepository districtAreaRepository;
    private final DistrictAreaMapper districtAreaMapper;

    @Override
    public PageDto<DistrictAreaViewDto> getPageView(DistrictAreaSearchDto dto, Pageable pageable) {
        Specification<DistrictArea> districtAreaSpecification = DistrictAreaSpecification.query(dto);
        return new PageDto<>(
                districtAreaRepository.findAll(districtAreaSpecification, pageable)
                        .map(districtAreaMapper::toDistrictAreaViewDto));
    }

    @Override
    public DistrictArea findOrCreateMigration(InstitutionDTO.AdmDivDTO admDivDTO, Region region) {
        if (Objects.isNull(admDivDTO) || Objects.isNull(region)) {
            return null;
        }

        String id = admDivDTO.getId();
        if (Objects.isNull(id)) {
            return null;
        }

        return districtAreaRepository.findByLocationId(Long.valueOf(id))
                .orElseGet(() -> {
                    DistrictArea districtArea = new DistrictArea();
                    districtArea.setLocationId(Long.valueOf(id));
                    districtArea.setName(admDivDTO.getName());
                    districtArea.setRegion(region);
                    return districtAreaRepository.save(districtArea);
                });
    }
}

package kz.talimger.service.impl;

import kz.talimger.dto.institution.InstitutionDTO;
import kz.talimger.model.DistrictArea;
import kz.talimger.model.Region;
import kz.talimger.repository.DistrictAreaRepository;
import kz.talimger.service.DistrictAreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DistrictAreaServiceImpl implements DistrictAreaService {

    private final DistrictAreaRepository districtAreaRepository;

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

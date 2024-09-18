package kz.talimger.service.impl;

import kz.talimger.dto.institution.InstitutionDTO;
import kz.talimger.model.Region;
import kz.talimger.model.Settlement;
import kz.talimger.repository.SettlementRepository;
import kz.talimger.service.SettlementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SettlementServiceImpl implements SettlementService {

    private final SettlementRepository settlementRepository;

    public Settlement findOrCreateMigration(InstitutionDTO.AdmDivDTO admDivDTO, Region region) {
        if (Objects.isNull(admDivDTO) || Objects.isNull(region)) {
            return null;
        }

        String id = admDivDTO.getId();
        if (Objects.isNull(id)) {
            return null;
        }

        return settlementRepository.findByLocationId(Long.valueOf(id))
                .orElseGet(() -> {
                    Settlement settlement = new Settlement();
                    settlement.setLocationId(Long.valueOf(id));
                    String cleanedName = admDivDTO.getName().replace("\u00A0", " ").trim();
                    settlement.setName(cleanedName);
                    settlement.setRegion(region);
                    return settlementRepository.save(settlement);
                });
    }
}

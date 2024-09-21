package kz.talimger.service.impl;

import kz.talimger.dto.common.PageDto;
import kz.talimger.dto.institution.InstitutionDTO;
import kz.talimger.dto.settlement.SettlementSearchDto;
import kz.talimger.dto.settlement.SettlementViewDto;
import kz.talimger.mapper.SettlementMapper;
import kz.talimger.model.Region;
import kz.talimger.model.Settlement;
import kz.talimger.repository.SettlementRepository;
import kz.talimger.service.SettlementService;
import kz.talimger.specification.SettlementSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class SettlementServiceImpl implements SettlementService {

    private final SettlementRepository settlementRepository;
    private final SettlementMapper settlementMapper;

    @Override
    public PageDto<SettlementViewDto> getPageView(SettlementSearchDto dto, Pageable pageable) {
        Specification<Settlement> settlementSpecification = SettlementSpecification.query(dto);
        return new PageDto<>(
                settlementRepository.findAll(settlementSpecification, pageable)
                        .map(settlementMapper::toSettlementViewDto));
    }

    @Override
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

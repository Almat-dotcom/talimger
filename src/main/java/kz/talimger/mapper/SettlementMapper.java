package kz.talimger.mapper;

import kz.talimger.dto.settlement.SettlementViewDto;
import kz.talimger.model.Settlement;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {RegionMapper.class})
public interface SettlementMapper {
    SettlementViewDto toSettlementViewDto(Settlement settlement);
}

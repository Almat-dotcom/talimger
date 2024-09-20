package kz.talimger.dto.settlement;

import kz.talimger.dto.region.RegionViewDto;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class SettlementViewDto {
    private UUID id;
    private String name;
    private RegionViewDto region;
}

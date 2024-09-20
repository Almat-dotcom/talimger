package kz.talimger.dto.settlement;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SettlementSearchDto {
    private String name;
    private UUID regionId;
}
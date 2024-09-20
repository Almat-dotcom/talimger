package kz.talimger.dto.kindergarten;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class KindergartenSearchDto {
    private String name;
    private UUID regionId;
    private UUID cityId;
    private UUID districtAreaId;
    private UUID settlementId;
}

package kz.talimger.dto.districtArea;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DistrictAreaSearchDto {
    private String name;
    private UUID regionId;
}

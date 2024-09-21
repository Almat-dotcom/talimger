package kz.talimger.dto.districtArea;

import kz.talimger.dto.region.RegionViewDto;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DistrictAreaViewDto {
    private UUID id;
    private String name;
    private RegionViewDto region;
}

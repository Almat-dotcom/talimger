package kz.talimger.dto.districtArea;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DistrictAreaViewDto {
    private String name;
    private UUID regionId;
}

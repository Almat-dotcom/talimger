package kz.talimger.dto.region;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RegionViewDto {
    private UUID id;
    private String name;
    private Long locationId;
}

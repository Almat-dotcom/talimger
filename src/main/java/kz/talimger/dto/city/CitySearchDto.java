package kz.talimger.dto.city;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CitySearchDto {
    private String name;
    private UUID regionId;
}

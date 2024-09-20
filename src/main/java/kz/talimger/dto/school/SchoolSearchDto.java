package kz.talimger.dto.school;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SchoolSearchDto {
    private String name;
    private UUID regionId;
    private UUID cityId;
    private UUID districtAreaId;
    private UUID settlementId;
}
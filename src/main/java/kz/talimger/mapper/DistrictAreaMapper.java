package kz.talimger.mapper;

import kz.talimger.dto.districtArea.DistrictAreaViewDto;
import kz.talimger.model.DistrictArea;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {RegionMapper.class})
public interface DistrictAreaMapper {
    DistrictAreaViewDto toDistrictAreaViewDto(DistrictArea districtArea);
}

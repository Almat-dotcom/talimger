package kz.talimger.mapper;

import kz.talimger.dto.region.RegionViewDto;
import kz.talimger.model.Region;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegionMapper {
    RegionViewDto toRegionViewDto(Region region);
}

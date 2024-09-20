package kz.talimger.mapper;

import kz.talimger.dto.city.CityViewDto;
import kz.talimger.model.City;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {RegionMapper.class})
public interface CityMapper {
    CityViewDto toCityViewDto(City city);
}

package kz.talimger.mapper;

import kz.talimger.dto.address.AddressViewDto;
import kz.talimger.model.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring",
        uses = {
                RegionMapper.class,
                CityMapper.class,
                DistrictAreaMapper.class,
                SettlementMapper.class
        })
public interface AddressMapper {
    AddressViewDto toAddressViewDto(Address address);
}

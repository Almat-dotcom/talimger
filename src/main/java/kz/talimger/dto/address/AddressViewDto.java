package kz.talimger.dto.address;

import kz.talimger.dto.city.CityViewDto;
import kz.talimger.dto.districtArea.DistrictAreaViewDto;
import kz.talimger.dto.region.RegionViewDto;
import kz.talimger.dto.settlement.SettlementViewDto;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AddressViewDto {
    private UUID id;
    private String addressName;
    private RegionViewDto region;
    private CityViewDto city;
    private DistrictAreaViewDto districtArea;
    private SettlementViewDto settlement;
}

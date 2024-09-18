package kz.talimger.service.impl;

import kz.talimger.dto.institution.InstitutionDTO;
import kz.talimger.model.*;
import kz.talimger.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdmDivServiceImpl implements AdmDivService {

    private final CountryService countryService;
    private final RegionService regionService;
    private final CityService cityService;
    private final DistrictService districtService;
    private final SettlementService settlementService;
    private final DistrictAreaService districtAreaService;

    public void processAdministrativeDivisions(InstitutionDTO dto, Address address, Institution institution) {
        if (Objects.isNull(dto.getAdmDiv()) || dto.getAdmDiv().isEmpty()) {
            return;
        }

        Map<String, InstitutionDTO.AdmDivDTO> admDivMap = dto.getAdmDiv().stream()
                .collect(Collectors.toMap(InstitutionDTO.AdmDivDTO::getType, Function.identity()));

        // Обработка страны
        Country country = countryService.findOrCreateMigration(admDivMap.get("country"));
        if (Objects.nonNull(address)) address.setCountry(country);

        // Обработка региона
        Region region = regionService.findOrCreateMigration(admDivMap.get("region"), country);
        institution.setRegion(region);
        if (Objects.nonNull(address)) address.setRegion(region);

        // Обработка города
        City city = cityService.findOrCreateMigration(admDivMap.get("city"), region);
        institution.setCity(city);
        if (Objects.nonNull(address)) address.setCity(city);

        // Обработка района
        District district = districtService.findOrCreateMigration(admDivMap.get("district"), country, city);
        if (Objects.nonNull(address)) address.setDistrict(district);

        // Обработка населённого пункта
        Settlement settlement = settlementService.findOrCreateMigration(admDivMap.get("settlement"), region);
        if (Objects.nonNull(address)) address.setSettlement(settlement);

        // Обработка административного округа
        DistrictArea districtArea = districtAreaService.findOrCreateMigration(admDivMap.get("district_area"), region);
        if (Objects.nonNull(address)) address.setDistrictArea(districtArea);
    }
}

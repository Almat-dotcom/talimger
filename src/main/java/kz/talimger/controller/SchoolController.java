package kz.talimger.controller;

import jakarta.transaction.Transactional;
import kz.talimger.dto.institution.InstitutionDTO;
import kz.talimger.enums.InstitutionEnum;
import kz.talimger.model.*;
import kz.talimger.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api/schools")
@RequiredArgsConstructor
@Transactional
public class SchoolController {
    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private RubricRepository rubricRepository;

    @Autowired
    private PointRepository pointRepository;

    @Autowired
    private RegionRepository regionRepository;
    private final DistrictRepository districtRepository;
    private final SettlementRepository settlementRepository;
    private final DistrictAreaRepository districtAreaRepository;

    @Autowired
    private CountryRepository countryRepository;

    private final AddressRepository addressRepository;


    /**
     * Endpoint to create or update a school based on InstitutionDTO.
     *
     * @return ResponseEntity with the saved school data.
     */
    @PostMapping
    public ResponseEntity<School> createOrUpdateSchool(@RequestBody List<InstitutionDTO> institutionDTOs) {
        for (InstitutionDTO institutionDTO : institutionDTOs) {

            School school = new School();
            school.setName(institutionDTO.getName());
            school.setLegalName(institutionDTO.getOrg().getName());
            school.setShortName(institutionDTO.getNameEx().getShortName());

            // Map the Address DTO to the Address entity
            Address address = null;
            if (institutionDTO.getAddress() != null) {
                address = addressRepository.findByBuildingId(institutionDTO.getAddress().getBuildingId())
                        .orElseGet(() -> {
                            Address newAddress = new Address();
                            if (institutionDTO.getAddress().getBuildingId() == null) {
                                System.out.println("Building ID is null");
                            }
                            newAddress.setBuildingId(institutionDTO.getAddress().getBuildingId());
                            newAddress.setAddressName(institutionDTO.getAddressName());
                            return newAddress;
                        });

                // Проверка на null перед доступом к объекту point
                if (institutionDTO.getPoint() != null) {
                    Point point = new Point();
                    point.setLatitude(institutionDTO.getPoint().getLat());
                    point.setLongitude(institutionDTO.getPoint().getLon());
                    point = pointRepository.save(point); // Сохраняем Point
                    school.setPoint(point); // Присваиваем Point в школу
                } else {
                    // Логика на случай, если Point не указан (например, установите null или другой обработчик)
                    school.setPoint(null);
                }


                // Set Address in School

                String countryId = institutionDTO.getAdmDiv().stream()
                        .filter(admDiv -> "country".equals(admDiv.getType()))
                        .map(InstitutionDTO.AdmDivDTO::getId)
                        .findFirst()
                        .orElse(null);

                Country country = null;
                if (countryId != null) {
                    country = countryRepository.findByLocationId(Long.valueOf(countryId)).orElseGet(() -> {
                        Country newCountry = new Country();
                        newCountry.setName(institutionDTO.getAdmDiv().stream()
                                .filter(admDiv -> "country".equals(admDiv.getType()))
                                .map(InstitutionDTO.AdmDivDTO::getName)
                                .findFirst()
                                .orElse(""));
                        newCountry.setLocationId(Long.valueOf(countryId));
                        return countryRepository.save(newCountry);
                    });
                }

                String regionId = institutionDTO.getAdmDiv().stream()
                        .filter(admDiv -> "region".equals(admDiv.getType()))
                        .map(InstitutionDTO.AdmDivDTO::getId)
                        .findFirst()
                        .orElse(null);

                Region region = null;
                if (regionId != null && country != null) {
                    Country finalCountry = country;
                    region = regionRepository.findByLocationId(Long.valueOf(regionId)).orElseGet(() -> {
                        Region newRegion = new Region();
                        newRegion.setName(institutionDTO.getAdmDiv().stream()
                                .filter(admDiv -> "region".equals(admDiv.getType()))
                                .map(InstitutionDTO.AdmDivDTO::getName)
                                .findFirst()
                                .orElse(""));
                        newRegion.setLocationId(Long.valueOf(regionId));
                        newRegion.setCountry(finalCountry); // Associate the region with the country
                        return regionRepository.save(newRegion);
                    });
                }

                // Map and save the City entity
                String cityId = institutionDTO.getAdmDiv().stream()
                        .filter(admDiv -> "city".equals(admDiv.getType()))
                        .map(InstitutionDTO.AdmDivDTO::getId)
                        .findFirst()
                        .orElse(null);

                String districtId = institutionDTO.getAdmDiv().stream()
                        .filter(admDiv -> "district".equals(admDiv.getType()))
                        .map(InstitutionDTO.AdmDivDTO::getId)
                        .findFirst()
                        .orElse(null);

                String settlementId = institutionDTO.getAdmDiv().stream()
                        .filter(admDiv -> "settlement".equals(admDiv.getType()))
                        .map(InstitutionDTO.AdmDivDTO::getId)
                        .findFirst()
                        .orElse(null);

                String districtAreaId = institutionDTO.getAdmDiv().stream()
                        .filter(admDiv -> "district_area".equals(admDiv.getType()))
                        .map(InstitutionDTO.AdmDivDTO::getId)
                        .findFirst()
                        .orElse(null);

                City city = null;
                District district = null;
                Settlement settlement = null;
                DistrictArea districtArea = null;
                if (cityId != null) {
                    Region finalRegion = region;
                    city = cityRepository.findByLocationId(Long.valueOf(cityId)).orElseGet(() -> {
                        City newCity = new City();
                        newCity.setName(institutionDTO.getAdmDiv().stream()
                                .filter(admDiv -> "city".equals(admDiv.getType()))
                                .map(InstitutionDTO.AdmDivDTO::getName)
                                .findFirst()
                                .orElse(""));
                        newCity.setLocationId(Long.valueOf(cityId));
                        newCity.setRegion(finalRegion);
                        return cityRepository.save(newCity);
                    });
                    school.setCity(city);
                } else if (districtId != null) {
                    Country finalCountry1 = country;
                    City finalCity = city;
                    district = districtRepository.findByLocationId(Long.valueOf(districtId)).orElseGet(() -> {
                        District newDistrict = new District();
                        newDistrict.setName(institutionDTO.getAdmDiv().stream()
                                .filter(admDiv -> "district".equals(admDiv.getType()))
                                .map(InstitutionDTO.AdmDivDTO::getName)
                                .findFirst()
                                .orElse(""));
                        newDistrict.setLocationId(Long.valueOf(districtId));
                        newDistrict.setCountry(finalCountry1);
                        newDistrict.setCity(finalCity);
                        return districtRepository.save(newDistrict);
                    });
                } else if (settlementId != null) {
                    Region finalRegion1 = region;
                    settlement = settlementRepository.findByLocationId(Long.valueOf(settlementId)).orElseGet(() -> {
                        Settlement newSettlement = new Settlement();
                        newSettlement.setName(institutionDTO.getAdmDiv().stream()
                                .filter(admDiv -> "settlement".equals(admDiv.getType()))
                                .map(InstitutionDTO.AdmDivDTO::getName)
                                .findFirst()
                                .orElse(""));
                        newSettlement.setLocationId(Long.valueOf(settlementId));
                        newSettlement.setRegion(finalRegion1);
                        return settlementRepository.save(newSettlement);
                    });
                } else if (districtAreaId != null) {
                    Region finalRegion2 = region;
                    districtArea = districtAreaRepository.findByLocationId(Long.valueOf(districtAreaId)).orElseGet(() -> {
                        DistrictArea newDistrictArea = new DistrictArea();
                        newDistrictArea.setName(institutionDTO.getAdmDiv().stream()
                                .filter(admDiv -> "district_area".equals(admDiv.getType()))
                                .map(InstitutionDTO.AdmDivDTO::getName)
                                .findFirst()
                                .orElse(""));
                        newDistrictArea.setLocationId(Long.valueOf(districtAreaId));
                        newDistrictArea.setRegion(finalRegion2);
                        return districtAreaRepository.save(newDistrictArea);
                    });
                }
                address.setRegion(region);
                address.setSettlement(settlement);
                address.setDistrict(district);
                address.setDistrictArea(districtArea);
                address.setCountry(country);
                address.setCity(city);
            }
            school.setAddress(address);

            // Map and save Rubrics
            List<Rubric> rubrics = institutionDTO.getRubrics().stream()
                    .map(rubricDTO -> rubricRepository.findByLocationId(rubricDTO.getId())
                            .orElseGet(() -> {
                                Rubric newRubric = new Rubric();
                                newRubric.setLocationId(rubricDTO.getId());
                                newRubric.setName(rubricDTO.getName());
                                newRubric.setAlias(rubricDTO.getAlias());
                                newRubric.setType(InstitutionEnum.SCHOOL);
                                return rubricRepository.save(newRubric);
                            }))
                    .toList();
            school.setRubrics(rubrics);
            if (address != null) {
                addressRepository.save(address);
            }
            schoolRepository.save(school);
        }
        return ResponseEntity.ok().build();
    }
}

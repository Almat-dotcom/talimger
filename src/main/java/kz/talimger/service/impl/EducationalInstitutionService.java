package kz.talimger.service.impl;

import kz.talimger.model.*;
import kz.talimger.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EducationalInstitutionService {

    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private RubricRepository rubricRepository;
    @Autowired
    private AddressRepository addressRepository;

}

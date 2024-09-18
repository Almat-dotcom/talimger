package kz.talimger.service.impl;

import kz.talimger.dto.institution.InstitutionDTO;
import kz.talimger.model.Address;
import kz.talimger.repository.AddressRepository;
import kz.talimger.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public Address processAddress(InstitutionDTO dto) {
        if (Objects.isNull(dto.getAddress())) {
            return null;
        }

        InstitutionDTO.AddressDTO.ComponentDTO component = dto.getAddress().getComponents().get(0);

        String buildingId = dto.getAddress().getBuildingId();
        String addressName = Objects.nonNull(dto.getAddressName()) ?
                dto.getAddressName() : dto.getAddress().getComponents().get(0).getComment().replace("\u00A0", " ").trim();
        String street = Objects.nonNull(component.getStreet()) ?
                (component.getStreet() + " " + component.getNumber()) : null;
        String addressComment = Objects.nonNull(component.getComment()) ?
                component.getComment().replace("\u00A0", " ").trim() : null;

        if (Objects.isNull(buildingId)) {
            return null;
        }

        return addressRepository.findByBuildingId(buildingId)
                .orElseGet(() -> {
                    Address address = new Address();
                    address.setBuildingId(buildingId);
                    address.setAddressName(addressName);
                    address.setStreet(street);
                    address.setAddressComment(addressComment);
                    return addressRepository.save(address);
                });
    }
}

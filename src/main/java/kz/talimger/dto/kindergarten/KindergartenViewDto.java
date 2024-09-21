package kz.talimger.dto.kindergarten;

import kz.talimger.dto.address.AddressViewDto;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class KindergartenViewDto {
    private UUID id;
    private String name;
    private String rubric;
    private AddressViewDto address;
}

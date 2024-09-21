package kz.talimger.dto.school;

import kz.talimger.dto.address.AddressViewDto;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SchoolViewDto {
    private UUID id;
    private String name;
    private String rubric;
    private AddressViewDto address;
}

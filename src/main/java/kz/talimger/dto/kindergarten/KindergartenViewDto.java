package kz.talimger.dto.kindergarten;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class KindergartenViewDto {
    private UUID id;
    private String name;
    private String address;
    private String rubric;
}

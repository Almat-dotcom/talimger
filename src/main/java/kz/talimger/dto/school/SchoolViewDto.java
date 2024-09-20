package kz.talimger.dto.school;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SchoolViewDto {
    private UUID id;
    private String name;
    private String address;
    private String rubric;
}

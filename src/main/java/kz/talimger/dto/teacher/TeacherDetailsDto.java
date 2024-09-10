package kz.talimger.dto.teacher;

import kz.talimger.dto.department.DepartmentDto;
import lombok.Data;

import java.util.UUID;

@Data
public class TeacherDetailsDto {
    private UUID id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String email;
    private DepartmentDto department;
}

package kz.talimger.mapper;

import kz.talimger.dto.department.CreateDepartmentDto;
import kz.talimger.dto.department.DepartmentDto;
import kz.talimger.model.Department;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    DepartmentDto toDto(Department department);
    Department toEntity(CreateDepartmentDto dto);
}

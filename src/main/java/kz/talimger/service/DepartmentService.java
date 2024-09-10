package kz.talimger.service;

import kz.talimger.dto.department.CreateDepartmentDto;
import kz.talimger.dto.department.DepartmentDto;
import kz.talimger.model.Department;

import java.util.List;
import java.util.UUID;

public interface DepartmentService {
    List<DepartmentDto> findAll();

    DepartmentDto findById(UUID id);

    DepartmentDto createDepartment(CreateDepartmentDto dto);

    DepartmentDto editDepartment(DepartmentDto departmentDto);

    void deleteDepartment(UUID id);

    Department find(UUID id);
}

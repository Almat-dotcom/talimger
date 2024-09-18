package kz.talimger.service.impl;

import kz.talimger.dto.department.CreateDepartmentDto;
import kz.talimger.dto.department.DepartmentDto;
import kz.talimger.exception.KazNpuException;
import kz.talimger.mapper.DepartmentMapper;
import kz.talimger.model.Department;
import kz.talimger.repository.DepartmentRepository;
import kz.talimger.service.DepartmentService;
import kz.talimger.util.ErrorCodeConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    public List<DepartmentDto> findAll() {
        return departmentRepository.findAll().stream()
                .map(departmentMapper::toDto)
                .toList();
    }

    public DepartmentDto findById(UUID id) {
        return departmentMapper.toDto(find(id));
    }

    public DepartmentDto createDepartment(CreateDepartmentDto dto) {
        if (departmentRepository.findByName(dto.getName()).isPresent()) {
            throw new KazNpuException(
                    HttpStatus.CONFLICT,
                    ErrorCodeConstant.DEPARTMENT_IS_EXIST,
                    "message.error.department-is-exist");
        }

        Department department = departmentRepository.save(departmentMapper.toEntity(dto));
        return departmentMapper.toDto(department);
    }

    public DepartmentDto editDepartment(DepartmentDto departmentDto) {
        Department department = find(departmentDto.getId());
        department.setName(departmentDto.getName());
        return departmentMapper.toDto(departmentRepository.save(department));
    }

    public void deleteDepartment(UUID id) {
        Department department = find(id);
        departmentRepository.delete(department);
    }

    public Department find(UUID id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new KazNpuException(HttpStatus.NOT_FOUND,
                        ErrorCodeConstant.DEPARTMENT_NOT_FOUND,
                        "message.error.department-not-found"));
    }
}
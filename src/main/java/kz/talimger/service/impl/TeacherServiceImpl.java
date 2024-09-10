package kz.talimger.service.impl;

import kz.talimger.dto.teacher.CreateTeacherDto;
import kz.talimger.dto.teacher.TeacherDetailsDto;
import kz.talimger.dto.teacher.TeacherDto;
import kz.talimger.enums.RoleEnum;
import kz.talimger.exception.KazNpuException;
import kz.talimger.mapper.TeacherMapper;
import kz.talimger.model.Teacher;
import kz.talimger.repository.RoleRepository;
import kz.talimger.repository.TeacherRepository;
import kz.talimger.service.DepartmentService;
import kz.talimger.service.TeacherService;
import kz.talimger.util.ErrorCodeConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final RoleRepository roleRepository;
    private final DepartmentService departmentService;
    private final TeacherMapper teacherMapper;

    @Override
    public List<TeacherDetailsDto> findAll() {
        return teacherRepository.findAll().stream()
                .map(teacherMapper::toDetailsDto)
                .toList();
    }

    @Override
    public TeacherDetailsDto findById(UUID id) {
        return teacherMapper.toDetailsDto(find(id));
    }

    @Override
    public TeacherDetailsDto createTeacher(CreateTeacherDto dto) {
        Teacher teacher = teacherRepository.save(teacherMapper.toEntity(dto));
        teacher.setRoles(Collections.singleton(roleRepository.findByName(RoleEnum.TEACHER.getCode())));
        return teacherMapper.toDetailsDto(teacher);
    }

    @Override
    public TeacherDetailsDto editTeacher(TeacherDto dto) {
        Teacher teacher = find(dto.getId());
        teacher.setFirstName(dto.getFirstName());
        teacher.setMiddleName(dto.getMiddleName());
        teacher.setLastName(dto.getLastName());
        teacher.setEmail(dto.getEmail());
        teacher.setDepartment(departmentService.find(dto.getDepartmentId()));
        return teacherMapper.toDetailsDto(teacherRepository.save(teacher));
    }

    @Override
    public void deleteTeacher(UUID id) {
        Teacher teacher = find(id);
        teacherRepository.delete(teacher);
    }

    public Teacher find(UUID id) {
        return teacherRepository.findById(id)
                .orElseThrow(() -> new KazNpuException(HttpStatus.NOT_FOUND,
                        ErrorCodeConstant.TEACHER_NOT_FOUND,
                        "message.error.teacher-not-found"));
    }
}

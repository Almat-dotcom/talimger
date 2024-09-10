package kz.talimger.service;

import kz.talimger.dto.teacher.CreateTeacherDto;
import kz.talimger.dto.teacher.TeacherDetailsDto;
import kz.talimger.dto.teacher.TeacherDto;
import kz.talimger.model.Teacher;

import java.util.List;
import java.util.UUID;

public interface TeacherService {
    List<TeacherDetailsDto> findAll();

    TeacherDetailsDto findById(UUID id);

    TeacherDetailsDto createTeacher(CreateTeacherDto dto);

    TeacherDetailsDto editTeacher(TeacherDto dto);

    void deleteTeacher(UUID id);

    Teacher find(UUID id);
}

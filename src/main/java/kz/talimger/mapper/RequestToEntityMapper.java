package kz.talimger.mapper;

import kz.talimger.model.Department;
import kz.talimger.model.Rubric;
import kz.talimger.model.Specialization;
import kz.talimger.model.Teacher;
import kz.talimger.repository.DepartmentRepository;
import kz.talimger.repository.SpecializationRepository;
import kz.talimger.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@Named("RequestToEntityMapper")
@RequiredArgsConstructor
public class RequestToEntityMapper {
    private final SpecializationRepository specializationRepository;
    private final TeacherRepository teacherRepository;
    private final DepartmentRepository departmentRepository;

    @Named("mapSpecialization")
    protected Specialization mapSpecialization(UUID specializationId) {
        return specializationRepository.findById(specializationId).orElse(null);
    }

    @Named("mapTeacher")
    protected Teacher mapTeacher(UUID teacherId) {
        return teacherRepository.findById(teacherId).orElse(null);
    }

    @Named("mapDepartment")
    protected Department mapDepartment(UUID departmentId) {
        return departmentRepository.findById(departmentId).orElse(null);
    }

    @Named("mapRubricsToString")
    protected String mapRubricsToString(List<Rubric> rubrics) {
        if (Objects.isNull(rubrics) || rubrics.isEmpty()) {
            return null;
        }
        return rubrics.stream()
                .map(Rubric::getName)
                .collect(Collectors.joining(", "));
    }
}

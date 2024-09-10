package kz.talimger.mapper;

import kz.talimger.dto.teacher.CreateTeacherDto;
import kz.talimger.dto.teacher.TeacherDetailsDto;
import kz.talimger.dto.teacher.TeacherDto;
import kz.talimger.model.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {
                RequestToEntityMapper.class,
                DepartmentMapper.class
        })
public interface TeacherMapper {
    @Mapping(target = "departmentId", source = "department.id")
    TeacherDto toDto(Teacher teacher);

    @Mapping(target = "department", source = "departmentId",
            qualifiedByName = {"RequestToEntityMapper", "mapDepartment"})
    Teacher toEntity(CreateTeacherDto dto);

    @Mapping(target = "department", source = "departmentId",
            qualifiedByName = {"RequestToEntityMapper", "mapDepartment"})
    Teacher toEntity(TeacherDto dto);

    TeacherDetailsDto toDetailsDto(Teacher teacher);
}

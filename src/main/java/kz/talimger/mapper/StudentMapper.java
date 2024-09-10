package kz.talimger.mapper;

import kz.talimger.dto.authentication.SignUpRequest;
import kz.talimger.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {RequestToEntityMapper.class})
public interface StudentMapper {
    @Mapping(target = "specialization", source = "specializationId",
            qualifiedByName = {"RequestToEntityMapper", "mapSpecialization"})
    @Mapping(target = "teacher", source = "teacherId",
            qualifiedByName = {"RequestToEntityMapper", "mapTeacher"})
    Student toEntity(SignUpRequest signUpRequest);
}

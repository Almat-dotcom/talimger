package kz.talimger.mapper;

import kz.talimger.dto.school.SchoolViewDto;
import kz.talimger.model.School;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {
                RequestToEntityMapper.class,
                AddressMapper.class
        })
public interface SchoolMapper {

    @Mapping(source = "rubrics", target = "rubric", qualifiedByName = "mapRubricsToString")
    SchoolViewDto toSchoolViewDto(School school);
}

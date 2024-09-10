package kz.talimger.mapper;

import kz.talimger.dto.specialization.CreateSpecializationDto;
import kz.talimger.dto.specialization.SpecializationDto;
import kz.talimger.model.Specialization;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SpecializationMapper {
    SpecializationDto toDto(Specialization specialization);

    Specialization toEntity(CreateSpecializationDto dto);
}

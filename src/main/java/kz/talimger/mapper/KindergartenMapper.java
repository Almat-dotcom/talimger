package kz.talimger.mapper;

import kz.talimger.dto.kindergarten.KindergartenViewDto;
import kz.talimger.model.Kindergarten;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {RequestToEntityMapper.class})
public interface KindergartenMapper {

    @Mapping(source = "address.addressName", target = "address")
    @Mapping(source = "rubrics", target = "rubric", qualifiedByName = "mapRubricsToString")
    KindergartenViewDto toKindergartenViewDto(Kindergarten kindergarten);
}

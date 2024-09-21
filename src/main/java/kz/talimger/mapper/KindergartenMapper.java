package kz.talimger.mapper;

import kz.talimger.dto.kindergarten.KindergartenViewDto;
import kz.talimger.model.Kindergarten;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",
        uses = {
                RequestToEntityMapper.class,
                AddressMapper.class
        })
public interface KindergartenMapper {

    @Mapping(source = "rubrics", target = "rubric", qualifiedByName = "mapRubricsToString")
    KindergartenViewDto toKindergartenViewDto(Kindergarten kindergarten);
}

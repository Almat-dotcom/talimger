package kz.talimger.specification;

import kz.talimger.dto.city.CitySearchDto;
import kz.talimger.model.City;
import kz.talimger.util.SpecificationBuilder;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

@UtilityClass
public class CitySpecification {
    public Specification<City> query(CitySearchDto searchDto) {
        Specification<City> specification = new SpecificationBuilder<>();

        if (Objects.nonNull(searchDto.getRegionId())) {
            specification = specification.and(regionFilter(searchDto));
        }

        if (Objects.nonNull(searchDto.getName())) {
            specification = specification.and(nameFilter(searchDto.getName()));
        }
        return specification;
    }

    public Specification<City> regionFilter(CitySearchDto searchDto) {
        return (root, cq, cb) -> cb.equal(root.join("region").get("id"), searchDto.getRegionId());
    }

    public Specification<City> nameFilter(String query) {
        return (root, cq, cb) -> cb.like(
                cb.lower(root.get(City.Fields.name)),
                "%" + query.trim().toLowerCase() + "%"
        );
    }
}

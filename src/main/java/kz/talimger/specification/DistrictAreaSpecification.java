package kz.talimger.specification;

import kz.talimger.dto.districtArea.DistrictAreaSearchDto;
import kz.talimger.model.DistrictArea;
import kz.talimger.util.SpecificationBuilder;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

@UtilityClass
public class DistrictAreaSpecification {
    public Specification<DistrictArea> query(DistrictAreaSearchDto searchDto) {
        Specification<DistrictArea> specification = new SpecificationBuilder<>();

        if (Objects.nonNull(searchDto.getRegionId())) {
            specification = specification.and(regionFilter(searchDto));
        }

        if (Objects.nonNull(searchDto.getName())) {
            specification = specification.and(nameFilter(searchDto));
        }
        return specification;
    }

    public Specification<DistrictArea> regionFilter(DistrictAreaSearchDto searchDto) {
        return (root, cq, cb) -> cb.equal(root.join("region").get("id"), searchDto.getRegionId());
    }

    public Specification<DistrictArea> nameFilter(DistrictAreaSearchDto searchDto) {
        return (root, cq, cb) -> cb.like(cb.lower(root.get("name")), "%" + searchDto.getName().toLowerCase() + "%");
    }
}

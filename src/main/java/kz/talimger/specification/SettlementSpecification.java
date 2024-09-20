package kz.talimger.specification;

import kz.talimger.dto.settlement.SettlementSearchDto;
import kz.talimger.model.Settlement;
import kz.talimger.util.SpecificationBuilder;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

@UtilityClass
public class SettlementSpecification {
    public Specification<Settlement> query(SettlementSearchDto searchDto) {
        Specification<Settlement> specification = new SpecificationBuilder<>();

        if (Objects.nonNull(searchDto.getRegionId())) {
            specification = specification.and(regionFilter(searchDto));
        }

        if (Objects.nonNull(searchDto.getName())) {
            specification = specification.and(nameFilter(searchDto));
        }
        return specification;
    }

    public Specification<Settlement> regionFilter(SettlementSearchDto searchDto) {
        return (root, cq, cb) -> cb.equal(root.join("region").get("id"), searchDto.getRegionId());
    }

    public Specification<Settlement> nameFilter(SettlementSearchDto searchDto) {
        return (root, cq, cb) -> cb.like(cb.lower(root.get("name")), "%" + searchDto.getName().toLowerCase() + "%");
    }
}

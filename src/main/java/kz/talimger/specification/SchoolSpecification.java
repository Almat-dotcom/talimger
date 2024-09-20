package kz.talimger.specification;

import jakarta.persistence.criteria.JoinType;
import kz.talimger.dto.school.SchoolSearchDto;
import kz.talimger.model.Institution;
import kz.talimger.model.Region;
import kz.talimger.model.School;
import kz.talimger.util.SpecificationBuilder;
import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;

import java.util.Objects;

@UtilityClass
public class SchoolSpecification {
    public Specification<School> query(SchoolSearchDto searchDto) {
        Specification<School> specification = new SpecificationBuilder<>();

        if (Objects.nonNull(searchDto.getRegionId())) {
            specification = specification.and(regionFilter(searchDto));
        }

        if (Objects.nonNull(searchDto.getCityId())) {
            specification = specification.and(cityFilter(searchDto));
        }

        if (Objects.nonNull(searchDto.getDistrictAreaId())) {
            specification = specification.and(districtAreaFilter(searchDto));
        }

        if (Objects.nonNull(searchDto.getSettlementId())) {
            specification = specification.and(settlementFilter(searchDto));
        }

        if (Objects.nonNull(searchDto.getName())) {
            specification = specification.and(nameFilter(searchDto));
        }
        return specification;
    }

    public Specification<School> regionFilter(SchoolSearchDto searchDto) {
        return (root, cq, cb) -> cb.equal(root.join("address").join("region", JoinType.LEFT).get("id"), searchDto.getRegionId());
    }

    public Specification<School> cityFilter(SchoolSearchDto searchDto) {
        return (root, cq, cb) -> cb.equal(root.join("address").join("city", JoinType.LEFT).get("id"), searchDto.getCityId());
    }

    public Specification<School> districtAreaFilter(SchoolSearchDto searchDto) {
        return (root, cq, cb) -> cb.equal(root.join("address").join("districtArea", JoinType.LEFT).get("id"), searchDto.getDistrictAreaId());
    }

    public Specification<School> settlementFilter(SchoolSearchDto searchDto) {
        return (root, cq, cb) -> cb.equal(root.join("address").join("settlement", JoinType.LEFT).get("id"), searchDto.getSettlementId());
    }

    public Specification<School> nameFilter(SchoolSearchDto searchDto) {
        return (root, cq, cb) -> cb.like(cb.lower(root.get("name")), "%" + searchDto.getName().toLowerCase() + "%");
    }
}

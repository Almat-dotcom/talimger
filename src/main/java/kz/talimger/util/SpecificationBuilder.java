package kz.talimger.util;


import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.lang.Nullable;

public class SpecificationBuilder<T> implements Specification<T> {
    private Specification<T> specification;

    public SpecificationBuilder() {
    }

    public Specification<T> and(@Nullable Specification<T> specification) {
        if (this.specification == null) {
            this.specification = specification;
        } else {
            this.specification = this.specification.and(specification);
        }

        return this;
    }

    public Specification<T> or(@Nullable Specification<T> specification) {
        if (this.specification == null) {
            this.specification = specification;
        } else {
            this.specification = this.specification.or(specification);
        }

        return this;
    }

    @Nullable
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        return this.specification == null ? null : this.specification.toPredicate(root, criteriaQuery, criteriaBuilder);
    }
}
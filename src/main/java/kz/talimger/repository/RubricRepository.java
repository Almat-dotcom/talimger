package kz.talimger.repository;

import kz.talimger.model.Rubric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RubricRepository extends JpaRepository<Rubric, UUID> {
    Optional<Rubric> findByAlias(String alias);

    Optional<Rubric> findByLocationId(String id);
}

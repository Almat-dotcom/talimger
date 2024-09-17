package kz.talimger.repository;

import kz.talimger.model.Kindergarten;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface KindergartenRepository extends JpaRepository<Kindergarten, UUID> {
}

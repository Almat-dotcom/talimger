package kz.talimger.repository;

import kz.talimger.model.Settlement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SettlementRepository extends JpaRepository<Settlement, UUID>, JpaSpecificationExecutor<Settlement> {
    Optional<Settlement> findByLocationId(Long locationId);
}

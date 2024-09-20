package kz.talimger.repository;

import kz.talimger.model.DistrictArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DistrictAreaRepository extends JpaRepository<DistrictArea, UUID>, JpaSpecificationExecutor<DistrictArea> {
    Optional<DistrictArea> findByLocationId(Long locationId);
}

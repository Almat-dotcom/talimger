package kz.talimger.repository;

import kz.talimger.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CityRepository extends JpaRepository<City, UUID>, JpaSpecificationExecutor<City> {
    Optional<City> findByLocationId(Long locationId);
}

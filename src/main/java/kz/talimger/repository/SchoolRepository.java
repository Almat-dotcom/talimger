package kz.talimger.repository;

import kz.talimger.model.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SchoolRepository extends JpaRepository<School, UUID> {
    List<School> findAllByAddress_BuildingId(String buildingId);
}
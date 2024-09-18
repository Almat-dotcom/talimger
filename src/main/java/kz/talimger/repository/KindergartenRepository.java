package kz.talimger.repository;

import kz.talimger.model.Kindergarten;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface KindergartenRepository extends JpaRepository<Kindergarten, UUID> {
    List<Kindergarten> findAllByAddress_BuildingId(String buildingId);
}

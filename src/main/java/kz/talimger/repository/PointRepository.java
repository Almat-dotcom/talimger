package kz.talimger.repository;

import kz.talimger.model.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface PointRepository extends JpaRepository<Point, UUID> {
}

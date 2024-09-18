package kz.talimger.service;

import kz.talimger.dto.institution.InstitutionDTO;
import kz.talimger.model.Point;

public interface PointService {
    Point processPoint(InstitutionDTO dto);
}

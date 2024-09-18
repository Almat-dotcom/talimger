package kz.talimger.service;

import kz.talimger.dto.institution.InstitutionDTO;
import kz.talimger.enums.InstitutionEnum;
import kz.talimger.model.Rubric;

import java.util.List;

public interface RubricService {
    List<Rubric> processRubricsMigration(InstitutionDTO dto, InstitutionEnum type);
}

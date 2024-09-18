package kz.talimger.service.impl;

import kz.talimger.dto.institution.InstitutionDTO;
import kz.talimger.enums.InstitutionEnum;
import kz.talimger.model.Rubric;
import kz.talimger.repository.RubricRepository;
import kz.talimger.service.RubricService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class RubricServiceImpl implements RubricService {

    private final RubricRepository rubricRepository;

    public List<Rubric> processRubricsMigration(InstitutionDTO dto, InstitutionEnum type) {
        if (Objects.isNull(dto.getRubrics()) || dto.getRubrics().isEmpty()) {
            return Collections.emptyList();
        }

        return dto.getRubrics().stream()
                .map(rubricDTO -> findOrCreateRubricMigration(rubricDTO, type))
                .filter(Objects::nonNull)
                .toList();
    }

    private Rubric findOrCreateRubricMigration(InstitutionDTO.RubricDTO rubricDTO, InstitutionEnum type) {
        String id = rubricDTO.getId();
        if (Objects.isNull(id)) {
            return null;
        }

        return rubricRepository.findByLocationId(id)
                .orElseGet(() -> {
                    Rubric rubric = new Rubric();
                    rubric.setLocationId(id);
                    rubric.setName(rubricDTO.getName());
                    rubric.setAlias(rubricDTO.getAlias());
                    rubric.setType(type.getCode());
                    return rubricRepository.save(rubric);
                });
    }
}

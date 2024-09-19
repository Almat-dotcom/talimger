package kz.talimger.controller;

import kz.talimger.dto.institution.InstitutionDTO;
import kz.talimger.service.KindergartenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/kindergartens")
@RequiredArgsConstructor
public class KindergartenController {

    private final KindergartenService kindergartenService;

    @PostMapping("/migration")
    public ResponseEntity<Void> processKindergartens(@RequestBody List<InstitutionDTO> institutionDTOs) {
        kindergartenService.processInstitutions(institutionDTOs);
        return ResponseEntity.ok().build();
    }
}

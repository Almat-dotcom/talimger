package kz.talimger.controller;

import kz.talimger.dto.institution.InstitutionDTO;
import kz.talimger.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/schools")
@RequiredArgsConstructor
public class SchoolController {
    private final SchoolService schoolService;

    @PostMapping("/migration")
    public ResponseEntity<Void> processSchools(@RequestBody List<InstitutionDTO> institutionDTOs) {
        schoolService.processInstitutions(institutionDTOs);
        return ResponseEntity.ok().build();
    }
}

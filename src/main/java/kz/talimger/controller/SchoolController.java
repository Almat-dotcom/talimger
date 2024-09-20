package kz.talimger.controller;

import kz.talimger.dto.institution.InstitutionDTO;
import kz.talimger.dto.school.SchoolSearchDto;
import kz.talimger.dto.school.SchoolViewDto;
import kz.talimger.service.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/schools")
@RequiredArgsConstructor
public class SchoolController {
    private final SchoolService schoolService;

    @GetMapping
    public ResponseEntity<Page<SchoolViewDto>> getSchools(@ModelAttribute SchoolSearchDto searchDto, Pageable pageable) {
        Page<SchoolViewDto> schoolPage = schoolService.getPageView(searchDto, pageable);
        return ResponseEntity.ok(schoolPage);
    }

    @PostMapping("/migration")
    public ResponseEntity<Void> processSchools(@RequestBody List<InstitutionDTO> institutionDTOs) {
        schoolService.processInstitutions(institutionDTOs);
        return ResponseEntity.ok().build();
    }
}

package kz.talimger.controller;

import jakarta.validation.Valid;
import kz.talimger.dto.specialization.CreateSpecializationDto;
import kz.talimger.dto.specialization.SpecializationDto;
import kz.talimger.service.SpecializationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/specialization")
@RequiredArgsConstructor
public class SpecializationController {
    private final SpecializationService specializationService;

    @GetMapping
    public ResponseEntity<List<SpecializationDto>> findAll() {
        return ResponseEntity.ok(specializationService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SpecializationDto> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(specializationService.findById(id));
    }

    @PostMapping
    public ResponseEntity<SpecializationDto> createSpecialization(@Valid @RequestBody CreateSpecializationDto dto) {
        return ResponseEntity.ok(specializationService.createSpecialization(dto));
    }

    @PutMapping
    public ResponseEntity<SpecializationDto> editSpecialization(@Valid @RequestBody SpecializationDto dto) {
        return ResponseEntity.ok(specializationService.editSpecialization(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpecialization(@PathVariable UUID id) {
        specializationService.deleteSpecialization(id);
        return ResponseEntity.ok().build();
    }
}

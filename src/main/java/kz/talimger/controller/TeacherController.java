package kz.talimger.controller;

import jakarta.validation.Valid;
import kz.talimger.dto.teacher.CreateTeacherDto;
import kz.talimger.dto.teacher.TeacherDetailsDto;
import kz.talimger.dto.teacher.TeacherDto;
import kz.talimger.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/teacher")
@RequiredArgsConstructor
public class TeacherController {
    private final TeacherService teacherService;

    @GetMapping
    public ResponseEntity<List<TeacherDetailsDto>> findAll() {
        return ResponseEntity.ok(teacherService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDetailsDto> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(teacherService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TeacherDetailsDto> createTeacher(@Valid @RequestBody CreateTeacherDto dto) {
        return ResponseEntity.ok(teacherService.createTeacher(dto));
    }

    @PutMapping
    public ResponseEntity<TeacherDetailsDto> editTeacher(@Valid @RequestBody TeacherDto dto) {
        return ResponseEntity.ok(teacherService.editTeacher(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeacher(@PathVariable UUID id) {
        teacherService.deleteTeacher(id);
        return ResponseEntity.ok().build();
    }
}

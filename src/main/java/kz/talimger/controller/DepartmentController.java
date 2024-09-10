package kz.talimger.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import kz.talimger.dto.department.CreateDepartmentDto;
import kz.talimger.dto.department.DepartmentDto;
import kz.talimger.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/department")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @Operation(summary = "Получить все департаменты", description = "Возвращает список всех департаментов.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно получен список департаментов",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DepartmentDto.class)))
    })
    @GetMapping
    public ResponseEntity<List<DepartmentDto>> findAll(){
        return ResponseEntity.ok(departmentService.findAll());
    }

    @Operation(summary = "Получить департамент по ID", description = "Возвращает данные об департаменте по его ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно получен департамент",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DepartmentDto.class))),
            @ApiResponse(responseCode = "404", description = "департамент не найден")
    })
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDto> findById(@PathVariable UUID id){
        return ResponseEntity.ok(departmentService.findById(id));
    }

    @Operation(summary = "Создать новый департамент", description = "Создает новый департамент на основе переданных данных.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно создан новый департамент",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DepartmentDto.class))),
            @ApiResponse(responseCode = "400", description = "Некорректные данные для создания департамента")
    })
    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(
            @Valid @RequestBody CreateDepartmentDto dto) {
        return ResponseEntity.ok(departmentService.createDepartment(dto));
    }

    @Operation(summary = "Редактировать департамент", description = "Редактирует данные департамента на основе переданных данных.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно обновлен департамент",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = DepartmentDto.class))),
            @ApiResponse(responseCode = "400", description = "Некорректные данные для редактирования департамента")
    })
    @PutMapping
    public ResponseEntity<DepartmentDto> editDepartment(
            @Valid @RequestBody DepartmentDto dto) {
        return ResponseEntity.ok(departmentService.editDepartment(dto));
    }

    @Operation(summary = "Удалить департамент", description = "Удаляет департамент по его ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно удален департамент"),
            @ApiResponse(responseCode = "404", description = "Департамент не найден")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable UUID id){
        departmentService.deleteDepartment(id);
        return ResponseEntity.ok().build();
    }
}

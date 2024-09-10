package kz.talimger.dto.department;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class DepartmentDto {
    @NotNull(message = "Id не может быть равен null")
    @Schema(description = "Id департамента")
    private UUID id;
    @NotBlank(message = "Имя не может быть пустым или равен null")
    @Schema(description = "Имя департамента")
    private String name;
}
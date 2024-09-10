package kz.talimger.dto.department;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateDepartmentDto {
    @NotBlank(message = "Имя не может быть пустым или равен null")
    @Schema(description = "Имя департамента")
    private String name;
}

package kz.talimger.dto.specialization;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateSpecializationDto {
    @NotBlank(message = "Имя не может быть пустым или равен null")
    @Schema(description = "Имя специализации")
    private String name;
}

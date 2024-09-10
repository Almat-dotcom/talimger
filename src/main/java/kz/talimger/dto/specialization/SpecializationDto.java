package kz.talimger.dto.specialization;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class SpecializationDto {
    @NotNull(message = "Id не может быть равен null")
    @Schema(description = "Id специализации")
    private UUID id;
    @NotBlank(message = "Имя не может быть пустым или равен null")
    @Schema(description = "Имя специализации")
    private String name;
}

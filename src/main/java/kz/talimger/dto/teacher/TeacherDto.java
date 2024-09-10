package kz.talimger.dto.teacher;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class TeacherDto {
    @NotNull(message = "Id не может быть равен null")
    @Schema(description = "Id учителя")
    private UUID id;
    @NotBlank(message = "Имя не может быть пустым или равен null")
    @Schema(description = "Имя учителя")
    private String firstName;
    @NotBlank(message = "Фамилия не может быть пустым или равен null")
    @Schema(description = "Фамилия учителя")
    private String lastName;
    @Schema(description = "Отчество учителя")
    private String middleName;
    @NotBlank(message = "Почта не может быть пустым или равен null")
    @Schema(description = "Почта учителя")
    private String email;
    @NotNull(message = "Департамент не может быть равен null")
    @Schema(description = "Департамент учите")
    private UUID departmentId;
}

package kz.talimger.dto.authentication;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.UUID;

@Data
public class SignUpRequest {
    @NotBlank(message = "Имя не может быть пустым или равен null")
    @Schema(description = "Имя пользователя")
    private String firstName;

    @NotBlank(message = "Фамилия не может быть пустым или равен null")
    @Schema(description = "Фамилия пользователя")
    private String lastName;

    @Schema(description = "Отчество пользователя")
    private String middleName;

    @NotNull(message = "Специализация не может быть равен null")
    @Schema(description = "Идентификатор специализации", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID specializationId;

    @NotBlank(message = "Курс не может быть пустым или равен null")
    @Schema(description = "Курс, на который зачислен студент", example = "Компьютерные науки")
    private String course;

    @NotNull(message = "Учитель не может быть равен null")
    @Schema(description = "Идентификатор преподавателя", example = "123e4567-e89b-12d3-a456-426614174001")
    private UUID teacherId;

    @NotBlank(message = "Почта не может быть пустым или равен null")
    @Schema(description = "Электронная почта", example = "user@example.com")
    @Email
    private String email;

    @NotBlank(message = "Пароль не может быть пустым или равен null")
    @Schema(description = "Пароль пользователя")
    private String password;

    @NotBlank(message = "Повторите пароль это поле не может быть пустым или равен null")
    @Schema(description = "Повторный ввод пароля")
    private String rePassword;
}

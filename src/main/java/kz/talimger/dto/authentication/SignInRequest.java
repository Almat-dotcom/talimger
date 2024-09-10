package kz.talimger.dto.authentication;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SignInRequest {
    @NotBlank(message = "Почта не может быть пустым или равен null")
    private String email;
    @NotBlank(message = "Пароль не может быть пустым или равен null")
    private String password;
}

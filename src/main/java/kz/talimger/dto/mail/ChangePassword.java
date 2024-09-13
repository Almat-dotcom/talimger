package kz.talimger.dto.mail;

public record ChangePassword(String email, String password, String rePassword) {
}

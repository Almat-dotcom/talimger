package kz.talimger.dto.forgotPassword;

import lombok.Data;

@Data
public class VerifyOtpDto {
    private Integer otp;
    private String email;
}

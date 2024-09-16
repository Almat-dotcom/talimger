package kz.talimger.controller;

import kz.talimger.dto.forgotPassword.VerifyMailDto;
import kz.talimger.dto.forgotPassword.VerifyOtpDto;
import kz.talimger.dto.mail.ChangePassword;
import kz.talimger.service.ForgotPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/forgot-password")
@RequiredArgsConstructor
public class ForgotPasswordController {
    private final ForgotPasswordService forgotPasswordService;

    @PostMapping("/verify/mail")
    public ResponseEntity<String> verifyEmail(@RequestBody VerifyMailDto verifyMailDto) {
        forgotPasswordService.verifyEmail(verifyMailDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/verify/otp")
    public ResponseEntity<String> verifyOtp(@RequestBody VerifyOtpDto dto) {
        forgotPasswordService.verifyOtp(dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePassword changePassword) {
        forgotPasswordService.changePassword(changePassword);
        return ResponseEntity.ok().build();
    }
}

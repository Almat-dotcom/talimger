package kz.talimger.controller;

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
    public ResponseEntity<String> verifyEmail(@RequestParam String email) {
        forgotPasswordService.verifyEmail(email);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/verify/otp")
    public ResponseEntity<String> verifyOtp(@RequestParam Integer otp, @RequestParam String email) {
        forgotPasswordService.verifyOtp(otp, email);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePassword changePassword) {
        forgotPasswordService.changePassword(changePassword);
        return ResponseEntity.ok().build();
    }
}

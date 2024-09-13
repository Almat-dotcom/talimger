package kz.talimger.service;

import kz.talimger.dto.mail.ChangePassword;

public interface ForgotPasswordService {
    void verifyEmail(String email);

    void verifyOtp(Integer otp, String email);

    void changePassword(ChangePassword changePassword);
}

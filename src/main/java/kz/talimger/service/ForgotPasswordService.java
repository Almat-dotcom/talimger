package kz.talimger.service;

import kz.talimger.dto.forgotPassword.VerifyMailDto;
import kz.talimger.dto.forgotPassword.VerifyOtpDto;
import kz.talimger.dto.mail.ChangePassword;

public interface ForgotPasswordService {
    void verifyEmail(VerifyMailDto dto);

    void verifyOtp(VerifyOtpDto dto);

    void changePassword(ChangePassword changePassword);
}

package kz.talimger.service.impl;

import jakarta.transaction.Transactional;
import kz.talimger.dto.forgotPassword.VerifyMailDto;
import kz.talimger.dto.forgotPassword.VerifyOtpDto;
import kz.talimger.dto.mail.ChangePassword;
import kz.talimger.dto.mail.MailBody;
import kz.talimger.exception.KazNpuException;
import kz.talimger.model.ForgotPassword;
import kz.talimger.model.User;
import kz.talimger.repository.ForgotPasswordRepository;
import kz.talimger.service.EmailService;
import kz.talimger.service.ForgotPasswordService;
import kz.talimger.service.UserService;
import kz.talimger.util.ErrorCodeConstant;
import kz.talimger.util.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class ForgotPasswordServiceImpl implements ForgotPasswordService {

    private static final SecureRandom secureRandom = new SecureRandom();
    private final ForgotPasswordRepository forgotPasswordRepository;
    private final EmailService emailService;
    private final UserService userService;
    private final MessageUtils messageUtils;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void verifyEmail(VerifyMailDto dto) {
        User user = userService.findUserByEmail(dto.getEmail());
        ForgotPassword fp = forgotPasswordRepository.findByUser(user).orElse(null);
        if (Objects.nonNull(fp)) {
            forgotPasswordRepository.delete(fp);
        }

        int otp = otpGenerator();
        MailBody mailBody = MailBody.builder()
                .to(dto.getEmail())
                .text(messageUtils.getMessage("message.inform.otp-text", String.valueOf(otp)))
                .subject(messageUtils.getMessage("message.inform.otp-subject"))
                .build();

        ForgotPassword newFp = ForgotPassword.builder()
                .otp(otp)
                .expirationDate(LocalDateTime.now().plusMinutes(5))
                .isVerified(false)
                .user(user)
                .build();

        emailService.sendSimpleMessage(mailBody);
        forgotPasswordRepository.save(newFp);
    }

    @Override
    public void verifyOtp(VerifyOtpDto dto) {
        User user = userService.findUserByEmail(dto.getEmail());

        ForgotPassword fp = forgotPasswordRepository.findByOtpAndUser(dto.getOtp(), user).orElseThrow(
                () -> new KazNpuException(HttpStatus.NOT_FOUND,
                        ErrorCodeConstant.INVALID_OTP,
                        "message.error.invalid-otp", dto.getEmail()));

        if (fp.getExpirationDate().isBefore(LocalDateTime.now())) {
            forgotPasswordRepository.deleteById(fp.getId());
            throw new KazNpuException(HttpStatus.EXPECTATION_FAILED,
                    ErrorCodeConstant.EXPIRED_OTP,
                    "message.error.expired-otp", dto.getEmail());
        }

        fp.setIsVerified(true);
        forgotPasswordRepository.save(fp);  // Save updated verification status
    }

    @Override
    public void changePassword(ChangePassword changePassword) {
        User user = userService.findUserByEmail(changePassword.email());

        ForgotPassword fp = forgotPasswordRepository.findByUser(user).orElseThrow(
                () -> new KazNpuException(HttpStatus.NOT_FOUND,
                        ErrorCodeConstant.INVALID_OTP,
                        "message.error.invalid-otp", changePassword.email()));

        if (Boolean.FALSE.equals(fp.getIsVerified())) {
            throw new KazNpuException(HttpStatus.BAD_REQUEST,
                    ErrorCodeConstant.OTP_NOT_VERIFIED,
                    "message.error.otp-not-verified", changePassword.email());
        }

        if (!changePassword.password().equals(changePassword.rePassword())) {
            throw new KazNpuException(HttpStatus.EXPECTATION_FAILED,
                    ErrorCodeConstant.PASSWORD_NOT_EQUAL,
                    "message.error.password-not-equal", changePassword.email());
        }

        String encodedPassword = passwordEncoder.encode(changePassword.password());
        userService.updatePassword(changePassword.email(), encodedPassword);

        forgotPasswordRepository.delete(fp);
    }

    private Integer otpGenerator() {
        return secureRandom.nextInt(900_000) + 100_000;  // Generate 6-digit OTP
    }
}

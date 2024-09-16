package kz.talimger.service.impl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import kz.talimger.dto.mail.MailBody;
import kz.talimger.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender javaMailSender;

    public void sendSimpleMessage(MailBody mailBody) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");

            helper.setTo(mailBody.to());
            helper.setFrom("asagyndykov06@gmail.com");
            helper.setSubject(mailBody.subject());
            helper.setText(mailBody.text(), false); // Установите в true, если используете HTML

            javaMailSender.send(message);
        }catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}

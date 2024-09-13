package kz.talimger.service;

import kz.talimger.dto.mail.MailBody;

public interface EmailService {
    void sendSimpleMessage(MailBody mailBody);
}

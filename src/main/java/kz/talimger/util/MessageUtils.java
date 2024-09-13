package kz.talimger.util;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
public class MessageUtils {

    private final MessageSource messageSource;

    // Инжектируем MessageSource через конструктор
    public MessageUtils(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    // Метод для получения сообщения с параметрами
    public String getMessage(String code, Object... args) {
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}
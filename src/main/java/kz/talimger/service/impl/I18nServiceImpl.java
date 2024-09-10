package kz.talimger.service.impl;

import kz.talimger.util.ErrorCodeConstant;
import kz.talimger.exception.KazNpuException;
import kz.talimger.service.I18nService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class I18nServiceImpl implements I18nService {

    MessageSource messageSource;

    /**
     * {@inheritDoc}
     */
    @Override
    public String getMessage(String code, Object... objects) {
        Locale locale = Locale.ENGLISH;
        String message = messageSource.getMessage(code, null,
                "Текст отсутсвует. Обратитесь в техподдержку", locale);
        if (objects != null && objects.length > 0) {
            if (StringUtils.isEmpty(message)) {
                throw new KazNpuException(
                        HttpStatus.NO_CONTENT,
                        ErrorCodeConstant.MESSAGE_CONTENT_EMPTY,
                        "messages.error.message_content_empty"
                );
            }
            return String.format(message, objects);
        } else {
            return message;
        }
    }
}
package kz.talimger.service;

public interface I18nService {

    /**
     * Получить интернализацию текста
     *
     * @param code    код текста
     * @param objects объекты которые парсятся в текст
     * @return текст
     */
    String getMessage(String code, Object... objects);
}
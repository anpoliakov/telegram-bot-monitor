package by.andrew.bot.telegrambotmonitor.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

/**
 *
 * Нужен для работы c файлом messages_ru_RU.properties (и другими локалями),
 * идёт в файл согласно локали - и берёт оттуда нужную строку по ключу
 *
 * */

@Service
public class LocaleMessageService {
    private final Locale locale;
    private final MessageSource messageSource;

    public LocaleMessageService(@Value("${localeTag}")String localeTag, MessageSource messageSource){
        this.locale = Locale.forLanguageTag(localeTag);
        this.messageSource = messageSource;
    }

    /* Отдаёт запрашиваенмое сообщение, согласно установленной локали 'localeTag' в application.properties  */
    public String getMessage(String message){
        System.out.println("Получаю message из property,по локали - " + locale.getCountry());
        return messageSource.getMessage(message,null, locale);
    }
}

package by.andrew.bot.telegrambotmonitor.config;

import by.andrew.bot.telegrambotmonitor.WebHookBot;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.client.RestTemplate;

/**
 *
 * Конфигурационный java класс, говорит Spring
 * какие бины нужно создать при старте и
 * положить в контейнер (по умолчанию все bean -
 * - singleton)
 *
 * */

@Setter
@Getter
@Configuration
public class AppConfig {
    private BotConfig botConfig;

    public AppConfig(BotConfig botConfig) {
        this.botConfig = botConfig;
    }

    /* главный класс бота */
    @Bean
    public WebHookBot getBot(){
        System.out.println("CREATED bean 'Bot' from AppConfig");
        return new WebHookBot(botConfig.getBotUserName(), botConfig.getBotToken(), botConfig.getWebHookURL());
    }

    /* управление файлом интернализации (язык) */
    @Bean
    public MessageSource messageSource(){
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages"); // Указываем базовое имя, используется в названии файла + .properties
        messageSource.setDefaultEncoding("UTF-8"); // Кодировка файла
        System.out.println("CREATED bean 'MessageSource' from AppConfig");
        return messageSource;
    }

    /* бин позволяет выполнять запросы на сторонние API */
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}

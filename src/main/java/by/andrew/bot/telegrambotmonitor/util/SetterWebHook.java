package by.andrew.bot.telegrambotmonitor.util;

import by.andrew.bot.telegrambotmonitor.dto.TelegramWebHook;
import by.andrew.bot.telegrambotmonitor.config.BotConfig;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 *
 * Устанавливает для Telegram Bot внешний WebHook адресс,
 * на который будет передавать JSON, а сам WebHook уже знает адресс PC
 * и передаёт информаию к нам
 *
 * */

@Component
public class SetterWebHook {
    private final String URL_TELEGRAM_BOT = "https://api.telegram.org/bot";
    private final String URL_METHOD_SET_WEBHOOK = "/setWebhook?url=";

    private BotConfig botConfig;
    private RestTemplate  restTemplate;

    public SetterWebHook(BotConfig botConfig, RestTemplate  restTemplate){
        this.botConfig = botConfig;
        this.restTemplate = restTemplate;
        setURLTelegramWebHook(createURL());
    }

    private String createURL(){
        return URL_TELEGRAM_BOT
                + botConfig.getBotToken()
                + URL_METHOD_SET_WEBHOOK
                + botConfig.getWebHookURL();
    }

    private void setURLTelegramWebHook(String url){
        TelegramWebHook telegramWebHook = restTemplate.getForObject(url, TelegramWebHook.class);
        System.out.println("WEBHOOK: " + telegramWebHook.getDescription());
    }
}

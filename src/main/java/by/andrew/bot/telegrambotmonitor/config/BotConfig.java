package by.andrew.bot.telegrambotmonitor.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 * Bean получает данные для подключения к боту
 * из файла application.properties по общему ключу "telegrambot",
 * и предоставляет доступ к этим данным
 *
 * */

@Component
@ConfigurationProperties(prefix = "telegrambot")
public class BotConfig {
    private String botUserName;
    private String botToken;
    private String webHookURL;

    public String getBotUserName() {
        return botUserName;
    }

    public void setBotUserName(String botUserName) {
        this.botUserName = botUserName;
    }

    public String getBotToken() {
        return botToken;
    }

    public void setBotToken(String botToken) {
        this.botToken = botToken;
    }

    public String getWebHookURL() {
        return webHookURL;
    }

    public void setWebHookURL(String webHookURL) {
        this.webHookURL = webHookURL;
    }
}

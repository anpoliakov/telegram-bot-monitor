package by.andrew.bot.telegrambotmonitor;

import by.andrew.bot.telegrambotmonitor.service.HandlerUpdates;
import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 *
 * Основной класс для работы с Telegram ботом
 *
 * */

public class WebHookBot extends TelegramWebhookBot {
    private String botUserName;
    private String botToken;
    private String webHookPath;

    @Autowired
    private HandlerUpdates handlerUpdates;

    public WebHookBot(String webHookPath, String botUserName, String botToken) {
        super();
        this.webHookPath = webHookPath;
        this.botUserName = botUserName;
        this.botToken = botToken;
    }

    /* 21.11.2020 */
    @Override
    public BotApiMethod onWebhookUpdateReceived(Update update) {
        return handlerUpdates.processUpdate(update);
    }

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public String getBotPath() {
        return webHookPath;
    }
}

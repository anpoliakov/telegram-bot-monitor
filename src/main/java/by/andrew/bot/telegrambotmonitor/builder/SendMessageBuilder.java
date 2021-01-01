package by.andrew.bot.telegrambotmonitor.builder;

import by.andrew.bot.telegrambotmonitor.botapi.BotState;
import by.andrew.bot.telegrambotmonitor.cache.UsersCache;
import by.andrew.bot.telegrambotmonitor.service.LocaleMessageService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

/**
 *
 * Класс предназначен для форировнаия готового ответа (SendMessage) - который содержит меню и/или текст сообщения
 *
 * */

@Service
public class SendMessageBuilder {
    private LocaleMessageService localeMessageService;           //Для работы с языковыми файлами шаблонов
    private UsersCache usersCache;                               //Хранит текущее состояние бота

    private SendMessage sendMessage;
    private Integer userID;
    private BotState botState;

    public SendMessageBuilder(LocaleMessageService localeMessageService, UsersCache usersCache) {
        this.localeMessageService = localeMessageService;
        this.usersCache = usersCache;
    }

    public SendMessage getSendMessage(Integer userID){
        this.userID = userID;
        botState = usersCache.getBotStateByUserID(userID);

        switch (botState){
            case CHOICE_LANGUAGE:
                sendMessage = createSendMessage("reply.greeting");
            break;
            case BASEMENU:
                sendMessage = createSendMessage("reply.showMenu");
            break;
            case CURRENT_RATE:
                sendMessage = createSendMessage("reply.currentCource");
            break;
        }
        return sendMessage;
    }

    private SendMessage createSendMessage(String messageKey){
        return (new SendMessage(String.valueOf(userID), localeMessageService.getMessage(messageKey)));
    }
}

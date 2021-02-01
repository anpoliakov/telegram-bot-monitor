package by.andrew.bot.telegrambotmonitor.builder;

import by.andrew.bot.telegrambotmonitor.botapi.BotState;
import by.andrew.bot.telegrambotmonitor.botapi.CurrencyAPI;
import by.andrew.bot.telegrambotmonitor.cache.UsersCache;
import by.andrew.bot.telegrambotmonitor.dto.Currency;
import by.andrew.bot.telegrambotmonitor.service.LocaleMessageService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private CurrencyAPI currencyAPI;

    public SendMessageBuilder(LocaleMessageService localeMessageService, UsersCache usersCache) {
        this.localeMessageService = localeMessageService;
        this.usersCache = usersCache;
    }

    public SendMessage getSendMessage(Integer userID){
        this.userID = userID;
        botState = usersCache.getBotStateByUserID(userID);

        switch (botState){
            case CHOICE_LANGUAGE:
                sendMessage = createSendMessageByKeyMessage("reply.greeting");
            break;
            case BASEMENU:
                sendMessage = createSendMessageByKeyMessage("reply.showMenu");
            break;
            case CURRENT_RATE:
                String message = localeMessageService.getMessage("reply.currentCource");
                Double rate = currencyAPI.getCurrencyRate().getCur_OfficialRate();
                sendMessage = createSendMessageWithMessage(message + " 1 USD = " + rate + " BYN");
                break;
        }
        return sendMessage;
    }

    private SendMessage createSendMessageByKeyMessage(String messageKey){
        return (new SendMessage(String.valueOf(userID), localeMessageService.getMessage(messageKey)));
    }

    private SendMessage createSendMessageWithMessage(String message){
        return (new SendMessage(String.valueOf(userID), message));
    }
}

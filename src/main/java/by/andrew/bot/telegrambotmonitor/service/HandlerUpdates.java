package by.andrew.bot.telegrambotmonitor.service;

import by.andrew.bot.telegrambotmonitor.botapi.BotState;
import by.andrew.bot.telegrambotmonitor.builder.KeyboardBuilder;
import by.andrew.bot.telegrambotmonitor.builder.SendMessageBuilder;
import by.andrew.bot.telegrambotmonitor.cache.UsersCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

/**
 *
 * Класс для обработки данных из обьекта 'Update'
 *
 * */

@Service
public class HandlerUpdates{
    @Autowired
    private KeyboardBuilder keyboardBuilder;

    @Autowired
    private SwitcherBotState switcherBotState;

    @Autowired
    private SendMessageBuilder sendMessageBuilder;

    @Autowired
    private HandlerCallbackQuery handlerCallbackQuery;

    /* основа */
    public BotApiMethod processUpdate(Update update){
        SendMessage sendMessage = null;
        Integer userID = null;


        //если получено сообщение
        if(isMessage(update)){
            userID = update.getMessage().getFrom().getId();
            switcherBotState.switchStateBot(update.getMessage());
        }

        //если пользователь нажал кнопку
        if(update.hasCallbackQuery()){
            userID = update.getCallbackQuery().getFrom().getId();
            handlerCallbackQuery.processChooseLanguage(update.getCallbackQuery());              //изменяю язык меню (если нужно)
        }

        sendMessage = sendMessageBuilder.getSendMessage(userID);
        keyboardBuilder.createKeyboard(sendMessage);
        return sendMessage;
    }

    /* Есть ли сообщение от пользователя ? */
    private boolean isMessage(Update update){
        boolean isMessage = false;
        if(update.hasMessage()){
            if(update.getMessage().getText() != null){
                isMessage = true;
            }
        }
        return isMessage;
    }

}


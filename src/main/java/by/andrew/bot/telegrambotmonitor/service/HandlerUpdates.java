package by.andrew.bot.telegrambotmonitor.service;

import by.andrew.bot.telegrambotmonitor.botapi.BotState;
import by.andrew.bot.telegrambotmonitor.builder.KeyboardBuilder;
import by.andrew.bot.telegrambotmonitor.builder.SendMessageBuilder;
import by.andrew.bot.telegrambotmonitor.cache.UsersCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
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

    @Autowired
    private UsersCache usersCache;

    /* основа */
    public BotApiMethod processUpdate(Update update){

        //если получено сообщение
        if(isMessage(update)){
            SendMessage sendMessage = null;
            Integer userID = update.getMessage().getFrom().getId();

            switcherBotState.switchStateBot(update.getMessage());
            sendMessage = sendMessageBuilder.getSendMessage(userID);
            keyboardBuilder.createKeyboard(sendMessage);
            return sendMessage;
        }

        //если пользователь нажал кнопку
        if(update.hasCallbackQuery()){
            Integer userID = update.getCallbackQuery().getFrom().getId();

            AnswerCallbackQuery answerCallbackQuery = handlerCallbackQuery.processCallback(update.getCallbackQuery());
            if(answerCallbackQuery != null){
                usersCache.setBotStateForUserID(userID, BotState.BASEMENU);
                SendMessage sendMessage = sendMessageBuilder.getSendMessage(userID);
                return keyboardBuilder.createKeyboard(sendMessage); 
            }
            return answerCallbackQuery;
        }

        return new SendMessage(String.valueOf(update.getMessage().getFrom().getId()), "DEFAULT ");
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


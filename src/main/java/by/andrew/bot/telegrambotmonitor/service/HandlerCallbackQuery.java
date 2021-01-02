package by.andrew.bot.telegrambotmonitor.service;

import by.andrew.bot.telegrambotmonitor.botapi.BotState;
import by.andrew.bot.telegrambotmonitor.cache.UsersCache;
import by.andrew.bot.telegrambotmonitor.util.ChangeLanguageTelegramBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

/**
 *
 * Обрабатывает нажатия на клавиатуру под сообщением (пока только выбор языка)
 *
 * */

@Service
public class HandlerCallbackQuery {

    @Autowired
    private UsersCache usersCache;

    public boolean processChooseLanguage(CallbackQuery callbackQuery){
        boolean isSetChooseLanguage = false;
        String textButton = callbackQuery.getData();
        Integer userID = callbackQuery.getFrom().getId();
        ChangeLanguageTelegramBot changeLanguage = new ChangeLanguageTelegramBot();

        if(textButton.equals("RUSSIAN")){
            changeLanguage.changeLanguageOn("ru-RU");
            isSetChooseLanguage = true;
            System.out.println("Руский язык установлен!");
        }else if(textButton.equals("ENGLISH")){
            changeLanguage.changeLanguageOn("EN");
            isSetChooseLanguage = true;
            System.out.println("Английский язык установлен!");
        }

        //в любом случае - переводим бота в состояние BASEMENU
        usersCache.setBotStateForUserID(userID, BotState.BASEMENU);

        return isSetChooseLanguage;
    }
}

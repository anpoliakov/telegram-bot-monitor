package by.andrew.bot.telegrambotmonitor.service;

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
    public AnswerCallbackQuery processCallback(CallbackQuery callbackQuery){
        String queryID = callbackQuery.getId();
        String textButton = callbackQuery.getData();

        AnswerCallbackQuery answerCallbackQuery = new AnswerCallbackQuery();
        answerCallbackQuery.setCallbackQueryId(queryID);
        answerCallbackQuery.setShowAlert(false);

        switch (textButton){
            case "RUSSIAN":
                System.out.println("Руский язык установлен!");
                answerCallbackQuery.setText("Руский язык установлен!");
            break;
            case "ENGLISH":
                System.out.println("Английский язык установлен!");
                answerCallbackQuery.setText("The English language is installed!");
            break;
        }

        return answerCallbackQuery;
    }
}

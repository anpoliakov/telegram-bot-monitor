package by.andrew.bot.telegrambotmonitor.service;

import by.andrew.bot.telegrambotmonitor.botapi.BotState;
import by.andrew.bot.telegrambotmonitor.cache.UsersCache;
import by.andrew.bot.telegrambotmonitor.util.ChangeLanguageTelegramBot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;

/**
 *
 * Класс отвечает за управление состоянием бота - взависимости
 * от поступившего сообщения от пользователя
 *
 * */

@Component
public class SwitcherBotState {
    @Autowired
    private UsersCache usersCache;                                  // Кэш файл для временных данных о состоянии бота

    @Autowired
    private MessageSource messageSource;

    public void switchStateBot(Message message){                    // Переключаем состояние бота - в зависимости от команды/сообщения в чат
        BotState currentState = null;                               // Текущее состояние бота
        Integer  userID = message.getFrom().getId();                // ID чата
        String textMsg = message.getText();                         // Сообщение

        switch (textMsg){
            case "Смена языка":
            case "/start":
                currentState = BotState.CHOICE_LANGUAGE;
                System.out.println("State - CHOICE_LANGUAGE");
            break;
            case "Меню":
                currentState = BotState.BASEMENU;
                System.out.println("State - BASEMENU");
            break;
            case "Текущий курс":
                currentState = BotState.CURRENT_RATE;
                System.out.println("State - CURRENT_RATE");
            break;
            case "> Русский":
                currentState = BotState.BASEMENU;
                System.out.println("Изменение меню на русский язык");
//                ChangeLanguageTelegramBot.changeLanguageOn("ru-RU");
            break;
            case "> English":
                currentState = BotState.BASEMENU;
                System.out.println("Изменение меню на English language");
//                ChangeLanguageTelegramBot.changeLanguageOn("EN");
            break;
            default:
                //если каким то образом null или не стандартное сообщение
                currentState = usersCache.getBotStateByUserID(userID);
                System.out.println("State - GETING STANDART STATE");
        }

        usersCache.setBotStateForUserID(userID, currentState);
    }
}

package by.andrew.bot.telegrambotmonitor.builder;

import by.andrew.bot.telegrambotmonitor.botapi.BotState;
import by.andrew.bot.telegrambotmonitor.cache.UsersCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;

/**
 *
 * Класс который отвечает за создание клавиатуры
 * для пользователя - взависимости от состояния
 *
 * */

@Service
public class KeyboardBuilder {
    @Autowired
    UsersCache usersCache;

    //создаёт клавиатуру для SendMessage - взависимости от состояния бота
    public SendMessage createReplyKeyboard(SendMessage sendMessage){
        Integer userID = Integer.valueOf(sendMessage.getChatId());            //Получаю ID user
        BotState currentState = usersCache.getBotStateByUserID(userID);
        System.out.println("ДЛя пользователя - " + userID);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();  //класс для вывода кнопок над клавиатурой
        ArrayList<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();                 //массив из строк (keyboardRow)
        KeyboardRow firstRow = new KeyboardRow();                             //первая строка keyboardRow
        KeyboardRow secondRow = new KeyboardRow();                            //вторая строка keyboardRow

        /* Настройки для отображаемой клавы */
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

// НУЖНО ЛИ ЭТО?
//        keyboard.clear();
//        firstRow.clear();
//        secondRow.clear();

        switch (currentState){
            case CHOICE_LANGUAGE:
                firstRow.add("> Русский");
                firstRow.add("> English");
            break;
            case BASEMENU:
                firstRow.add("Текущий курс");
                firstRow.add("Смена языка");
                secondRow.add("Настройки");
                secondRow.add("Помощь");
            break;
            case CURRENT_RATE:

            break;
        }

        keyboard.add(firstRow);
        keyboard.add(secondRow);
        replyKeyboardMarkup.setKeyboard(keyboard);
        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        return sendMessage;
    }
}






//    public SendMessage createInlineKeyboard(SendMessage sendMessage){
//        // Класс для вывода кнопок под сообщением
//        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
//
//        // Список кнопок
//        List<InlineKeyboardButton> keyboardButtonsRow = new ArrayList<>();
//
//        // Первая кнопка
//        InlineKeyboardButton inlineButton1 = new InlineKeyboardButton();
//        inlineButton1.setText("Русский");
//        inlineButton1.setCallbackData("Russian");
//
//        // Вторая кнопка
//        InlineKeyboardButton inlineButton2 = new InlineKeyboardButton();
//        inlineButton2.setText("English");
//        inlineButton2.setCallbackData("English");
//
//        keyboardButtonsRow.add(inlineButton1);
//        keyboardButtonsRow.add(inlineButton2);
//
//        List<List<InlineKeyboardButton>> rowsList = new ArrayList<>();
//        rowsList.add(keyboardButtonsRow);
//
//        inlineKeyboardMarkup.setKeyboard(rowsList);
//        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
//        return sendMessage;
//    }

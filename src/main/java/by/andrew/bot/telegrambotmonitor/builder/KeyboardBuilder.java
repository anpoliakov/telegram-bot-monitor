package by.andrew.bot.telegrambotmonitor.builder;

import by.andrew.bot.telegrambotmonitor.botapi.BotState;
import by.andrew.bot.telegrambotmonitor.cache.UsersCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

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

    //метод определяет какого типа клавиатуру необходимо создать
    public SendMessage createKeyboard(SendMessage sendMessage){
        Integer userID = Integer.valueOf(sendMessage.getChatId());
        BotState curStateBot = usersCache.getBotStateByUserID(userID);

        if(curStateBot.equals(BotState.CHOICE_LANGUAGE)){
            return createInlineKeyboard(sendMessage);
        }

        return createReplyKeyboard(sendMessage);
    }

    //клавиатура под сообщением
    private SendMessage createInlineKeyboard(SendMessage sendMessage){

        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();         //класс для вывода кнопок ПОД сообщением
        List<InlineKeyboardButton> buttonsRow = new ArrayList<>();                      //массив кнопок в одной строке
        List<List<InlineKeyboardButton>> rowsList = new ArrayList<>();                  //массив всех рядов с кнопками

        // Первая кнопка
        InlineKeyboardButton firstButton = new InlineKeyboardButton();
        firstButton.setText("Русский");
        firstButton.setCallbackData("RUSSIAN");

        // Вторая кнопка
        InlineKeyboardButton secondButton = new InlineKeyboardButton();
        secondButton.setText("English");
        secondButton.setCallbackData("ENGLISH");

        buttonsRow.add(firstButton);
        buttonsRow.add(secondButton);
        rowsList.add(buttonsRow);

        inlineKeyboardMarkup.setKeyboard(rowsList);
        sendMessage.setReplyMarkup(inlineKeyboardMarkup);

        return sendMessage;
    }

    //клавиатура над пользовательской клавиатурой
    private SendMessage createReplyKeyboard(SendMessage sendMessage){
        Integer userID = Integer.valueOf(sendMessage.getChatId());                       //Получаю ID user
        BotState currentState = usersCache.getBotStateByUserID(userID);
        System.out.println("ДЛя пользователя - " + userID);

        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();            //класс для вывода кнопок над клавиатурой
        ArrayList<KeyboardRow> keyboard = new ArrayList<KeyboardRow>();                           //массив из строк (keyboardRow)
        KeyboardRow firstRow = new KeyboardRow();                                       //первая строка keyboardRow
        KeyboardRow secondRow = new KeyboardRow();                                      //вторая строка keyboardRow

        /* Настройки для отображаемой клавы */
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        // НУЖНО ЛИ ЭТО?
        //keyboard.clear();
        //firstRow.clear();
        //secondRow.clear();

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
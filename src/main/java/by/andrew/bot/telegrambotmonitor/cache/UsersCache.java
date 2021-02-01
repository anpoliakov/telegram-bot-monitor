package by.andrew.bot.telegrambotmonitor.cache;

import by.andrew.bot.telegrambotmonitor.botapi.BotState;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * Класс - кэш, для хранения данных о состоянии бота у определённого пользователя
 *
 * */

@Component
public class UsersCache implements Cache{
    private Map <Integer, BotState> usersBotState = new HashMap<>();
    //private Map <Long, UserData> usersData = new HashMap<>();

    @Override
    public void setBotStateForUserID(Integer userID, BotState botState) {
        usersBotState.put(userID, botState);
    }

    @Override
    public BotState getBotStateByUserID(Integer userID) {
        if(usersBotState.containsKey(userID)){
            return usersBotState.get(userID);
        }else {
            return BotState.CHOICE_LANGUAGE;
        }
    }
}

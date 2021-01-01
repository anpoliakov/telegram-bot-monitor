package by.andrew.bot.telegrambotmonitor.cache;

import by.andrew.bot.telegrambotmonitor.botapi.BotState;

public interface Cache {
    void setBotStateForUserID(Integer userID, BotState botState);
    BotState getBotStateByUserID(Integer userID);
}

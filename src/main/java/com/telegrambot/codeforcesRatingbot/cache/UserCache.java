package com.telegrambot.codeforcesRatingbot.cache;

import com.telegrambot.codeforcesRatingbot.bot.BotState;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserCache {

    private Map<Integer, BotState> UserBotState = new HashMap<>();

    public void setUserBotState(int userId, BotState botState) {
        UserBotState.put(userId, botState);
    }

    public BotState getCurrentBotState(int userId) {
        BotState botState = UserBotState.get(userId);
        if (botState == null) {
            botState = BotState.SHOW_MAIN_MENU;
            UserBotState.put(userId, botState);
        }
        return botState;
    }
}

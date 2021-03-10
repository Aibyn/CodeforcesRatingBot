package com.telegrambot.codeforcesRatingbot.reply;

import com.telegrambot.codeforcesRatingbot.sender.CommonMessages;
import com.telegrambot.codeforcesRatingbot.bot.BotState;
import com.telegrambot.codeforcesRatingbot.cache.UserCache;
import com.telegrambot.codeforcesRatingbot.sender.MainMenuKeyboardMessages;
import com.telegrambot.codeforcesRatingbot.util.Emojis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
public class MenuReply implements Reply {

    @Autowired
    MainMenuKeyboardMessages mainMenuKeyboardMessages;
    @Autowired
    CommonMessages messageService;
    @Autowired
    UserCache userCache;

    @Override
    public SendMessage sendMessage(Message message) {
        userCache.setUserBotState(message.getFrom().getId(), BotState.NULL_STATE);
        return mainMenuKeyboardMessages.sendMessage(message.getChatId(), messageService.getLocaleMessageEmoji("reply.main.welcome", Emojis.ROBOT_FACE));
    }

    @Override
    public BotState getReplyName() {
        return BotState.SHOW_MAIN_MENU;
    }
}

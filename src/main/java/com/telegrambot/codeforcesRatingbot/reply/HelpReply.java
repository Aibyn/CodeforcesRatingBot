package com.telegrambot.codeforcesRatingbot.reply;

import com.telegrambot.codeforcesRatingbot.bot.BotState;
import com.telegrambot.codeforcesRatingbot.cache.UserCache;
import com.telegrambot.codeforcesRatingbot.sender.CommonMessages;
import com.telegrambot.codeforcesRatingbot.sender.MainMenuKeyboardMessages;
import com.telegrambot.codeforcesRatingbot.util.Emojis;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
@NoArgsConstructor
public class HelpReply implements Reply {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MainMenuKeyboardMessages mainMenuKeyboardMessages;
    @Autowired
    UserCache userCache;
    @Autowired
    CommonMessages messageService;

    @Value("${subscriptions.processPeriod}")
    String intervalString;


    @Override
    public SendMessage sendMessage(Message message) {
        userCache.setUserBotState(message.getFrom().getId(), BotState.NULL_STATE);
        return mainMenuKeyboardMessages.sendMessage(message.getChatId(), messageService.getLocaleMessageObjects("reply.help.welcome", Emojis.ROBOT_FACE, getIntervalMinutes(), Emojis.ADVICE));
    }

    private int getIntervalMinutes() {
        return Integer.parseInt(intervalString) / 1000 / 60;
    }

    @Override
    public BotState getReplyName() {
        return BotState.SHOW_HELP_MENU;
    }
}

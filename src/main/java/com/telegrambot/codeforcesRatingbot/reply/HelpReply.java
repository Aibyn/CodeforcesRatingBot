package com.telegrambot.codeforcesRatingbot.reply;

import com.telegrambot.codeforcesRatingbot.sender.CommonMessages;
import com.telegrambot.codeforcesRatingbot.bot.BotState;
import com.telegrambot.codeforcesRatingbot.cache.UserCache;
import com.telegrambot.codeforcesRatingbot.sender.MainMenuKeyboardMessages;
import com.telegrambot.codeforcesRatingbot.service.CommandGetterService;
import com.telegrambot.codeforcesRatingbot.util.Emojis;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.List;

@Service
@NoArgsConstructor
public class HelpReply implements Reply {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MainMenuKeyboardMessages mainMenuKeyboardMessages;
    @Autowired
    UserCache userCache;
    @Autowired
    CommonMessages messageService;

    @Override
    public SendMessage sendMessage(Message message) {
        userCache.setUserBotState(message.getFrom().getId(), BotState.NULL_STATE);
        return mainMenuKeyboardMessages.sendMessage(message.getChatId(), messageService.getLocaleMessageObjects("reply.help.welcome", Emojis.ADVICE));
    }

    @Override
    public BotState getReplyName() {
        return BotState.SHOW_HELP_MENU;
    }
}

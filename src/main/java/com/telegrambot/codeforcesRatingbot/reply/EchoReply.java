package com.telegrambot.codeforcesRatingbot.reply;

import com.telegrambot.codeforcesRatingbot.sender.CommonMessages;
import com.telegrambot.codeforcesRatingbot.bot.BotState;
import com.telegrambot.codeforcesRatingbot.cache.UserCache;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
@NoArgsConstructor
public class EchoReply implements Reply {

    @Autowired
    CommonMessages messageService;
    @Autowired
    UserCache userCache;

    @Override
    public SendMessage sendMessage(Message message) {
        userCache.setUserBotState(message.getFrom().getId(), BotState.NULL_STATE);
        return messageService.sendMessage(message.getChatId(), "Please use \"/\" commands for new action");
    }

    @Override
    public BotState getReplyName() {
        return BotState.NULL_STATE;
    }
}
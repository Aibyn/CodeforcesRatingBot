package com.telegrambot.codeforcesRatingbot.reply;

import com.telegrambot.codeforcesRatingbot.bot.BotState;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface Reply {
    SendMessage sendMessage(Message message);

    BotState getReplyName();
}

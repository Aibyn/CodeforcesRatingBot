package com.telegrambot.codeforcesRatingbot.Reply;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface Reply {
    public SendMessage sendMessage(Message message);
}

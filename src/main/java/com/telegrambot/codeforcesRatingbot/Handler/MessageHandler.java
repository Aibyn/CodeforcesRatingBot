package com.telegrambot.codeforcesRatingbot.Handler;

import com.telegrambot.codeforcesRatingbot.Bot;
import com.telegrambot.codeforcesRatingbot.Reply.EchoReply;
import com.telegrambot.codeforcesRatingbot.Reply.HelpReply;
import com.telegrambot.codeforcesRatingbot.Reply.Reply;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

@Component
public class MessageHandler {

    @Autowired
    Bot bot;

    public SendMessage handle(Message message) {
        User user = message.getFrom();
        String text = message.getText();
        Reply reply = null;
        switch (text) {
            case "/help":
                reply = new HelpReply(bot);
                break;
            default:
                reply = new EchoReply();
        }
        return reply.sendMessage(message);
    }
}

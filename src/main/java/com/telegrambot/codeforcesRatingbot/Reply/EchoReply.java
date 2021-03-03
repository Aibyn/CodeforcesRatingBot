package com.telegrambot.codeforcesRatingbot.Reply;

import com.telegrambot.codeforcesRatingbot.Sender.CommonMessages;
import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@NoArgsConstructor
public class EchoReply implements Reply {

    @Override
    public SendMessage sendMessage(Message message) {
        return CommonMessages.sendMessage(message.getChatId(), message.getText());
    }
}

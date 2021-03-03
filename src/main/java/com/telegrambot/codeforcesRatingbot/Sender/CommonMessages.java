package com.telegrambot.codeforcesRatingbot.Sender;

import lombok.NoArgsConstructor;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@NoArgsConstructor
public class CommonMessages {
    public static SendMessage sendMessage(Long chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(Long.toString(chatId));
        sendMessage.setText(text);
        return sendMessage;
    }
}

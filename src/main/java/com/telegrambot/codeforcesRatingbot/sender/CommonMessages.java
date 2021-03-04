package com.telegrambot.codeforcesRatingbot.sender;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@NoArgsConstructor
@Service
public class CommonMessages {
    public SendMessage sendMessage(Long chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(Long.toString(chatId));
        sendMessage.setText(text);
        return sendMessage;
    }

    public SendMessage sendWarningMessage(long chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(Long.toString(chatId));
        sendMessage.setText(text);
        return sendMessage;
    }

}

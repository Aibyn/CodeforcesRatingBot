package com.telegrambot.codeforcesRatingbot;

import com.telegrambot.codeforcesRatingbot.Handler.MessageHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class Bot extends TelegramLongPollingBot {

    @Autowired
    MessageHandler messageHandler;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${telegram.name}")
    private String BOT_NAME;
    @Value("${telegram.token}")
    private String BOT_TOKEN;

    public Bot() {
    }

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (!(update.getMessage() != null && update.getMessage().hasText())) {
            return;
        }
        Message message = update.getMessage();
        SendMessage sendMessage = messageHandler.handle(message);
        ExecuteSendMessage(sendMessage);
    }

    void ExecuteSendMessage(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}

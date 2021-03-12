package com.telegrambot.codeforcesRatingbot.bot;

import com.telegrambot.codeforcesRatingbot.handler.UpdateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramWebhookBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class Bot extends TelegramWebhookBot {

    @Autowired
    UpdateHandler updateHandler;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${telegram.name}")
    private String BOT_NAME;
    @Value("${telegram.token}")
    private String BOT_TOKEN;
    @Value("${telegram.bot.webHookPath}")
    private String WEB_HOOK_PATH;

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
    public String getBotPath() {
        return WEB_HOOK_PATH;
    }

    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        if (!(update.getMessage() != null && update.getMessage().hasText())) {
            return null;
        }
        Message message = update.getMessage();
        SendMessage sendMessage = updateHandler.handle(message);
        return sendMessage;
    }

    public void executeSendMessage(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}

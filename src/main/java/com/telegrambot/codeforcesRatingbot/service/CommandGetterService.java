package com.telegrambot.codeforcesRatingbot.service;

import com.telegrambot.codeforcesRatingbot.bot.Bot;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.commands.GetMyCommands;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommandGetterService {

    public CommandGetterService(@Lazy Bot telegramBot) {
        this.telegramBot = telegramBot;
    }
    Bot telegramBot;
    public List<BotCommand> getCommands() {
        try {
            ArrayList<BotCommand> botCommands = telegramBot.execute(new GetMyCommands());
            return botCommands;
        } catch (TelegramApiException e) {
            e.printStackTrace();
            return null;
        }
    }
}

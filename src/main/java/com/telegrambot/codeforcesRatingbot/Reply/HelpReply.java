package com.telegrambot.codeforcesRatingbot.Reply;

import com.telegrambot.codeforcesRatingbot.Bot;
import com.telegrambot.codeforcesRatingbot.Sender.CommonMessages;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.commands.GetMyCommands;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import java.util.ArrayList;

@Service
@NoArgsConstructor
public class HelpReply implements Reply {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public HelpReply(@Lazy Bot telegramBot) {
        this.telegramBot = telegramBot;
    }

    @Autowired
    Bot telegramBot;


    @Override
    public SendMessage sendMessage(Message message) {
        SendMessage replyToMessage = null;
        try {
            ArrayList<BotCommand> botCommands = telegramBot.execute(new GetMyCommands());
            StringBuilder commands = new StringBuilder();
            commands.append("Here is the list commands you can use\n");
            botCommands.forEach(botCommand -> {
                commands.append("/" + botCommand.getCommand() + " - " + botCommand.getDescription() + '\n');
            });
            commands.deleteCharAt(commands.length() - 1);
            replyToMessage = CommonMessages.sendMessage(message.getChatId(), commands.toString());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        return replyToMessage;
    }
}

package com.telegrambot.codeforcesRatingbot.Reply;

import com.telegrambot.codeforcesRatingbot.bot.Bot;
import com.telegrambot.codeforcesRatingbot.Sender.CommonMessages;
import com.telegrambot.codeforcesRatingbot.bot.BotState;
import com.telegrambot.codeforcesRatingbot.cache.UserCache;
import com.telegrambot.codeforcesRatingbot.service.CommandGetterService;
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
import java.util.List;

@Service
@NoArgsConstructor
public class HelpReply implements Reply {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CommandGetterService commandGetterService;
    @Autowired
    CommonMessages commonMessages;
    @Autowired
    UserCache userCache;

    @Override
    public SendMessage sendMessage(Message message) {
        userCache.setUserBotState(message.getFrom().getId(), BotState.NULL_STATE);
        List<BotCommand> botCommands = commandGetterService.getCommands();
        if (botCommands == null) {
            return commonMessages.sendWarningMessage(message.getChatId(), "Couldn't load commands. Please try again next time");
        }
        StringBuilder commands = new StringBuilder();
        commands.append("Here is the list commands you can use\n");
        botCommands.forEach(botCommand -> {
            commands.append("/" + botCommand.getCommand() + " - " + botCommand.getDescription() + '\n');
        });
        commands.deleteCharAt(commands.length() - 1);
        return commonMessages.sendMessage(message.getChatId(), commands.toString());
    }

    @Override
    public BotState getReplyName() {
        return BotState.SHOW_HELP_MENU;
    }
}

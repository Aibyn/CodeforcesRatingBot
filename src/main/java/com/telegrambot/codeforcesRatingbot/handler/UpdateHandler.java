package com.telegrambot.codeforcesRatingbot.handler;

import com.telegrambot.codeforcesRatingbot.reply.EchoReply;
import com.telegrambot.codeforcesRatingbot.reply.Reply;
import com.telegrambot.codeforcesRatingbot.bot.BotState;
import com.telegrambot.codeforcesRatingbot.cache.UserCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UpdateHandler {
    @Autowired
    UserCache userCache;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    Map<BotState, Reply> replyToBotState = new HashMap<>();

    public UpdateHandler(List<Reply> replyList) {
        replyList.forEach(reply -> replyToBotState.put(reply.getReplyName(), reply));
    }

    public SendMessage handle(Message message) {
        User user = message.getFrom();
        String text = message.getText();
        BotState botState = switch (text) {
            case "/help", "❓Help" -> BotState.SHOW_HELP_MENU;
            case "/add_profile", "\uD83D\uDD14Add Profile" -> BotState.SUBSCRIPTION_START;
            case "/delete_profile", "\uD83D\uDDD1Delete Profile" -> BotState.UNSUBSCRIPTION_START;
            case "/list_profile", "\uD83D\uDCDCShow Profile List" -> BotState.SHOW_SUBSCRIPTION_LIST;
            default -> userCache.getCurrentBotState(user.getId());
        };
        //TODO (aibyn) Add /cancel method
        userCache.setUserBotState(user.getId(), botState);
        Reply reply = handleReplyToBotState(botState);
        logger.info("This is my reply and BotState -> {} and {}", reply, botState);
        return reply.sendMessage(message);
    }

    private Reply handleReplyToBotState(BotState botState) {
        if (isProfileUpdate(botState)) {
            return replyToBotState.get(BotState.SUBSCRIPTION_START);
        }
        if (botState == null) {
            return new EchoReply();
        }
        return replyToBotState.get(botState);
    }

    private boolean isProfileUpdate(BotState botState) {
        return switch (botState) {
            case SUBSCRIPTION_START, UNSUBSCRIPTION_START -> true;
            default -> false;
        };
    }
}

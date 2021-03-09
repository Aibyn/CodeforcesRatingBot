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

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    Map<BotState, Reply> replyToBotState = new HashMap<>();

    public UpdateHandler(List<Reply> replyList) {
        replyList.forEach(reply -> {replyToBotState.put(reply.getReplyName(), reply);});
    }

    public SendMessage handle(Message message) {
        User user = message.getFrom();
        String text = message.getText();
        BotState botState = null;
        //TODD(aibyn) Add /cancel method
        switch (text) {
            case "/help":
            case "Help":
                botState = BotState.SHOW_HELP_MENU;
                break;
            case "/add_profile":
            case "Add Profile":
                botState = BotState.SUBSCRIPTION_START;
                break;
            case "/delete_profile":
            case "Delete Profile":
                botState = BotState.UNSUBSCRIPTION_START;
                break;
            case "/list_profile":
            case "Show Profile List":
                botState = BotState.SHOW_SUBSCRIPTION_LIST;
                break;
            default:
                botState = userCache.getCurrentBotState(user.getId());
        }
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
        switch (botState) {
            case SUBSCRIPTION_START:
            case UNSUBSCRIPTION_START:
                return true;
            default:
                return false;
        }
    }
}

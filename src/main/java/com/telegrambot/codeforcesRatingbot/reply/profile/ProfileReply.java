package com.telegrambot.codeforcesRatingbot.reply.profile;

import com.telegrambot.codeforcesRatingbot.reply.Reply;
import com.telegrambot.codeforcesRatingbot.sender.CommonMessages;
import com.telegrambot.codeforcesRatingbot.bot.BotState;
import com.telegrambot.codeforcesRatingbot.cache.UserCache;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
@NoArgsConstructor
public class ProfileReply implements Reply {
    @Autowired
    UserCache userCache;

    @Autowired
    CommonMessages messageService;

    @Override
    public SendMessage sendMessage(Message message) {
        int userId = message.getFrom().getId();
        ProfileState profileState = getProfileStateThroughUser(userCache.getCurrentBotState(userId));
        userCache.setUserBotState(message.getFrom().getId(), (profileState.equals(ProfileState.SUBSCRIBE) ? BotState.SUBSCRIPTION_PROCESS : BotState.UNSUBSCRIPTION_PROCESS));
        String action = (profileState.equals(ProfileState.SUBSCRIBE) ? "follow" : "unfollow");
        return messageService.sendMessage(message.getChatId(), "Please write codeforces username that you want to " + action);
    }

    private ProfileState getProfileStateThroughUser(BotState currentBotState) {
        if (currentBotState.equals(BotState.SUBSCRIPTION_START)) {
            return ProfileState.SUBSCRIBE;
        } else {
            return ProfileState.UNSUBSCRIBE;
        }
    }

    @Override
    public BotState getReplyName() {
        return BotState.SUBSCRIPTION_START;
    }
}

package com.telegrambot.codeforcesRatingbot.reply.profile;

import com.telegrambot.codeforcesRatingbot.bot.BotState;
import com.telegrambot.codeforcesRatingbot.cache.UserCache;
import com.telegrambot.codeforcesRatingbot.model.RatingChange;
import com.telegrambot.codeforcesRatingbot.model.UserRatingSubscription;
import com.telegrambot.codeforcesRatingbot.reply.Reply;
import com.telegrambot.codeforcesRatingbot.sender.CommonMessages;
import com.telegrambot.codeforcesRatingbot.service.InfoRetrievingService;
import com.telegrambot.codeforcesRatingbot.service.UserRatingService;
import com.telegrambot.codeforcesRatingbot.util.Emojis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
public class ProfileSubscribeReply implements Reply {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserRatingService subscriptionService;
    @Autowired
    CommonMessages messageService;
    @Autowired
    UserCache userCache;
    @Autowired
    InfoRetrievingService infoRetrievingService;

    @Override
    public SendMessage sendMessage(Message message) {
        String profile = message.getText();
        long chatId = message.getChatId();
        int userId = message.getFrom().getId();
        RatingChange ratingChange = null;
        try {
            ratingChange = infoRetrievingService.retrieveRatingChangeByUsername(profile);
        } catch (HttpClientErrorException.BadRequest e) {
            return messageService.sendWarningMessage(chatId, "reply.profile.action.subscribe.userError");
        } catch (RuntimeException e) {
            return messageService.sendMessage(chatId, "reply.profile.action.subscribe.serverError", Emojis.FAIL_SERVER_MARK);
        }
        int lastContestParticipated = (ratingChange == null ? 0 : ratingChange.getContestId());
        subscriptionService.saveUserSubscription(new UserRatingSubscription(chatId, lastContestParticipated, profile));

        logger.info("Chat id = {} subscribed to -> {}", chatId, profile);
        userCache.setUserBotState(userId, BotState.NULL_STATE);
        return messageService.sendSuccessMessage(message.getChatId(), "reply.profile.action.subscribe.success");
    }

    @Override
    public BotState getReplyName() {
        return BotState.SUBSCRIPTION_PROCESS;
    }
}

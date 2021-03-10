package com.telegrambot.codeforcesRatingbot.reply.profile;

import com.telegrambot.codeforcesRatingbot.bot.BotState;
import com.telegrambot.codeforcesRatingbot.cache.UserCache;
import com.telegrambot.codeforcesRatingbot.model.UserRatingSubscription;
import com.telegrambot.codeforcesRatingbot.reply.Reply;
import com.telegrambot.codeforcesRatingbot.sender.CommonMessages;
import com.telegrambot.codeforcesRatingbot.service.UserRatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

@Service
public class ProfileList implements Reply {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserRatingService subscriptionService;
    @Autowired
    CommonMessages messageService;
    @Autowired
    UserCache userCache;

    @Override
    public SendMessage sendMessage(Message message) {
        long chatId = message.getChatId();
        List<UserRatingSubscription> profileList = subscriptionService.findByChatId(chatId);
        if (profileList.size() == 0) {
            return messageService.sendMessage(chatId, "reply.list.profiles.noData");
        }
        StringBuilder stringBuilder = new StringBuilder();
        profileList.forEach(userRatingSubscription -> {
            stringBuilder.append("*" + userRatingSubscription.getProfile() + "*\n");
        });
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return messageService.sendMessage(chatId, "reply.list.profiles", stringBuilder.toString());
    }

    @Override
    public BotState getReplyName() {
        return BotState.SHOW_SUBSCRIPTION_LIST;
    }
}

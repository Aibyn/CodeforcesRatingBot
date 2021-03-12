package com.telegrambot.codeforcesRatingbot.service;

import com.telegrambot.codeforcesRatingbot.bot.Bot;
import com.telegrambot.codeforcesRatingbot.model.RatingChange;
import com.telegrambot.codeforcesRatingbot.model.UserRatingSubscription;
import com.telegrambot.codeforcesRatingbot.sender.CommonMessages;
import com.telegrambot.codeforcesRatingbot.util.Emojis;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledRatingService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserRatingRepositoryService userRatingRepositoryService;

    @Autowired
    InfoRetrievingService infoRetrievingService;

    @Autowired
    Bot telegramBot;

    @Autowired
    CommonMessages messageService;
    //TODO(aibyn) Change the way to process subscriptions. Better to get contests in whole.
    @Scheduled(fixedRateString = "${subscriptions.processPeriod}")
    private void processAllSubscriptions() {
        logger.info("Stars subscription process services");
        userRatingRepositoryService.allSubscription().forEach(this::handleSubscription);
        logger.info("Ends subscription process services");
    }

    public void handleSubscription(UserRatingSubscription subscription) {
        long chatId = subscription.getChatId();
        long lastContest = subscription.getLastContest();
        String profile = subscription.getProfile();
        //TODO (aibyn) Reacting different depending on rating change.
        try {
            RatingChange ratingChange = infoRetrievingService.retrieveRatingChangeByUsername(profile);

            if (ratingChange == null) {
                return;
            }

            int contestId = ratingChange.getContestId();
            if (contestId == lastContest) {
                return;
            }

            int ratingDiff = ratingChange.getNewRating() - ratingChange.getOldRating();
            String sign = (ratingDiff >= 0 ? "+" : "");
            String contestTitle = ratingChange.getContestName();

            telegramBot.executeSendMessage(messageService.sendMessage(chatId,
                    "reply.scheduled.subscription.ratingChange",
                    Emojis.NEW, (sign.equals("+") ? Emojis.UPWARD_TREND : Emojis.DOWNWARD_TREND), profile, sign, ratingDiff, contestTitle, contestId));
        } catch (RuntimeException e) {
            logger.warn("Couldn't get subscription information from Codeforces API");
        }
    }


}

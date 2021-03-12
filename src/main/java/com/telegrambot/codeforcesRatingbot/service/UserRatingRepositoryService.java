package com.telegrambot.codeforcesRatingbot.service;

import com.telegrambot.codeforcesRatingbot.model.UserRatingSubscription;
import com.telegrambot.codeforcesRatingbot.repository.UserSpringDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserRatingRepositoryService {
    @Autowired
    UserSpringDataRepository userSpringDataRepository;

    public Optional<UserRatingSubscription> findByChatIdAndProfile(long chatId, String profile) {
        return userSpringDataRepository.findByChatIdAndProfile(chatId, profile).stream().findFirst();
    }

    public List<UserRatingSubscription> findByChatId(long chatId) {
        return userSpringDataRepository.findByChatId(chatId);
    }
    public void saveUserSubscription(UserRatingSubscription userRatingSubscription) {
        userSpringDataRepository.save(userRatingSubscription);
    }

    public void deleteUserSubscription(UserRatingSubscription userRatingSubscription) {
        userSpringDataRepository.delete(userRatingSubscription);
    }

    public List<UserRatingSubscription> allSubscription() {
        return userSpringDataRepository.findAll();
    }
}

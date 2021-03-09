package com.telegrambot.codeforcesRatingbot.service;

import com.telegrambot.codeforcesRatingbot.model.UserRatingSubscription;
import com.telegrambot.codeforcesRatingbot.repository.UserSpringDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRatingService {
    @Autowired
    UserSpringDataRepository userSpringDataRepository;

    public List<UserRatingSubscription> allSubscription() {
        return userSpringDataRepository.findAll();
    }

    public List<UserRatingSubscription> findByChatId(long chadId) {
        return userSpringDataRepository.findByChatId(chadId);
    }
    public void saveUserSubscription(UserRatingSubscription userRatingSubscription) {
        userSpringDataRepository.save(userRatingSubscription);
    }

    public void deleteUserSubscription(UserRatingSubscription userRatingSubscription) {
        userSpringDataRepository.delete(userRatingSubscription);
    }
}

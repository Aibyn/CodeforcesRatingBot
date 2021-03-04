package com.telegrambot.codeforcesRatingbot.repository;

import com.telegrambot.codeforcesRatingbot.model.UserRatingSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserSpringDataRepository extends JpaRepository<UserRatingSubscription, String> {
    List<UserRatingSubscription> findByChatId(long userId);
}

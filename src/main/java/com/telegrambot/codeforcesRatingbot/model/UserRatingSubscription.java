package com.telegrambot.codeforcesRatingbot.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class UserRatingSubscription {
    @Id
    @GeneratedValue
    private long id;

    private long chatId;

    private long lastContest;

    private String profile;

    public UserRatingSubscription(long chatId, long lastContest, String profile) {
        this.chatId = chatId;
        this.lastContest = lastContest;
        this.profile = profile;
    }
}

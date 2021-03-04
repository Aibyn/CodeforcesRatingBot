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

    private String profile;

    public UserRatingSubscription(long chatId, String profile) {
        this.chatId = chatId;
        this.profile = profile;
    }
}

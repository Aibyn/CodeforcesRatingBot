package com.telegrambot.codeforcesRatingbot.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class RatingChange {
    private int contestId;

    private String contestName;

    private String handle;

    private int rank;

    @JsonProperty(value = "ratingUpdateTimeSeconds")
    private long ratingLastUpdate;

    private int oldRating;

    private int newRating;
}

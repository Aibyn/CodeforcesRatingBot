package com.telegrambot.codeforcesRatingbot.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.telegrambot.codeforcesRatingbot.model.RatingChange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class InfoRetrievingService {

    String codeforcesApiUrl = "https://codeforces.com/api/";
    String codeforcesGetUserRating = "user.rating";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final RestTemplate restTemplate;

    public InfoRetrievingService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    private ObjectMapper objectMapper;

    public RatingChange retrieveRatingChangeByUsername(String username) {
        String url = codeforcesApiUrl + codeforcesGetUserRating + "?handle=" + username;
        int tryCount = 5;
        boolean again = true;
        RatingChange ratingChange = null;
        while (again && tryCount > 0) {
            tryCount--;
            try {
                String jsonString = restTemplate.getForObject(url, String.class);
                try {
                    JsonNode jsonNode = objectMapper.readTree(jsonString).findPath("result");
                    RatingChange[] ratingChanges = objectMapper.readValue(jsonNode.toString(), RatingChange[].class);
                    if (ratingChanges.length > 0) {
                        ratingChange = ratingChanges[ratingChanges.length - 1];
                    }
                    again = false;
                } catch (JsonProcessingException e) {
                    again = false;
                    e.printStackTrace();
                    throw new RuntimeException("Couldn't parse the Json");
                }
            } catch (HttpClientErrorException.TooManyRequests e) {
                logger.warn("Too many requests to Codeforces API");
            } catch (HttpClientErrorException.BadRequest e) {
                logger.info("Non existent username={}", username);
                throw e;
            } catch (HttpClientErrorException e) {
                logger.warn("Http 400 exceptions -> {} for this username -> {}", e.getLocalizedMessage(), username);
                throw new RuntimeException("Couldn't access. Http 400 type error" );
            }
        }
        if (again) {
            throw new RuntimeException("Couldn't get username too many requests");
        }
        return ratingChange;
    }
}

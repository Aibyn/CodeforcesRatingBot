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

    @Value("${codeforces.api}")
    String codeforcesApiUrl;
    @Value("${codeforces.get.user.rating}")
    String codeforcesGetUserRating;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final RestTemplate restTemplate;

    public InfoRetrievingService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    private ObjectMapper objectMapper;

    public void retrieveJson(String username) {
        String url = codeforcesApiUrl + codeforcesGetUserRating + "?handle=" + username;
        try {
            String jsonString = restTemplate.getForObject(url, String.class);
            try {
                JsonNode jsonNode = objectMapper.readTree(jsonString).findPath("result");
                RatingChange[] ratingChanges = objectMapper.readValue(jsonNode.toString(), RatingChange[].class);
                if (ratingChanges.length > 0) {
                    logger.info("Here is the first rating change of profile -> {}", ratingChanges[ratingChanges.length - 1].toString());
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } catch (HttpClientErrorException.TooManyRequests e) {
            logger.warn("Too many requests to Codeforces API");
        } catch (HttpClientErrorException e) {
            logger.warn("Http 400 exceptions -> {}", e.getLocalizedMessage());
        }
    }
}

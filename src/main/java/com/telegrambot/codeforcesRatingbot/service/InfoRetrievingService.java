package com.telegrambot.codeforcesRatingbot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class InfoRetrievingService {

    @Value("${codeforces.api}")
    String codeforcesApiUrl;
    @Value("${codeforces.get.user.rating}")
    String codeforcesGetUserRating;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RestTemplate restTemplate;

    public InfoRetrievingService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void retrieveJson(String username) {
        //https://codeforces.com/api/user.rating?
        String url = codeforcesApiUrl + codeforcesGetUserRating + "?handle=" + username;
        String jsonString = restTemplate.getForObject(url, String.class);
        logger.info("This is my json string from codeforces get -> {}", jsonString);
        return;
    }
}

package com.telegrambot.codeforcesRatingbot.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class PingService {
    @Value("${pingtask.url}")
    private String url;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String getUrl() {
        return url;
    }

    @Scheduled(fixedRateString = "${pingtask.period}")
    @Scheduled(fixedRateString = "${pingtask.period}")
    public void pingMe() {
        try {
            URL url = new URL(getUrl());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            logger.info("Ping {}, OK: response code {}", url.getHost(), connection.getResponseCode());
            connection.disconnect();
        } catch (IOException e) {
            logger.error("Ping FAILED");
            e.printStackTrace();
        }
    }
}

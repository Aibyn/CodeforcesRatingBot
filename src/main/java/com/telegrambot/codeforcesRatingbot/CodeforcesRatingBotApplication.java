package com.telegrambot.codeforcesRatingbot;

import com.telegrambot.codeforcesRatingbot.model.UserRatingSubscription;
import com.telegrambot.codeforcesRatingbot.service.InfoRetrievingService;
import com.telegrambot.codeforcesRatingbot.service.ScheduledRatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableScheduling
public class CodeforcesRatingBotApplication {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(CodeforcesRatingBotApplication.class, args);
	}
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
}

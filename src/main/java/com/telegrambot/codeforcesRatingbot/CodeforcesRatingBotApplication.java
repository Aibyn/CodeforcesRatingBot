package com.telegrambot.codeforcesRatingbot;

import com.telegrambot.codeforcesRatingbot.service.InfoRetrievingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class CodeforcesRatingBotApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	InfoRetrievingService infoService;

	public static void main(String[] args) {
		SpringApplication.run(CodeforcesRatingBotApplication.class, args);
	}
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("Here start get codeforces");
		infoService.retrieveJson("Shadow");
	}
}

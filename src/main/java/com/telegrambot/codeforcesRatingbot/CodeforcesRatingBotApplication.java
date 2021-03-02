package com.telegrambot.codeforcesRatingbot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class CodeforcesRatingBotApplication {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(CodeforcesRatingBotApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	@Bean
	public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
		return args -> {
			String update = restTemplate.getForObject("https://api.telegram.org/bot1100803566:AAF-rM7svo1PfH8QNuZu6PRt14zMEmo4rNg/getUpdates", String.class);
			logger.info("This is my Object -> {}", update.toString());
			Map<String, String> obj = new HashMap<>();
			obj.put("chat_id", "322938800");
			obj.put("text", "Hello");
			restTemplate.postForObject("https://api.telegram.org/bot1100803566:AAF-rM7svo1PfH8QNuZu6PRt14zMEmo4rNg/sendMessage", obj, Map.class);
		};
	}
}

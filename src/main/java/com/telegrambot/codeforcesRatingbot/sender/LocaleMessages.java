package com.telegrambot.codeforcesRatingbot.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Component
public class LocaleMessages {
    private final Locale locale;
    private final MessageSource messageSource;
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public LocaleMessages(@Value("${locale.tag}") String localeTag, MessageSource messageSource) {
        this.locale = Locale.getDefault();
        this.messageSource = messageSource;
    }

    public String getMessage(String message) {
        return messageSource.getMessage(message, null, locale);
    }

    public String getMessage(String message, Object... args) {
        return messageSource.getMessage(message, args, locale);
    }
}

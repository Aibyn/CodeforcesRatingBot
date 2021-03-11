package com.telegrambot.codeforcesRatingbot.sender;

import com.telegrambot.codeforcesRatingbot.util.Emojis;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Service
public class MainMenuKeyboardMessages {
    public SendMessage sendMessage(long chatId, String testMessage) {
        ReplyKeyboardMarkup keyboardMarkup = mainMenuKeyboard();
        return CreateMessage(chatId, testMessage, keyboardMarkup);
    }

    private SendMessage CreateMessage(long chatId, String testMessage, ReplyKeyboardMarkup keyboardMarkup) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(testMessage);
        sendMessage.setChatId(Long.toString(chatId));
        sendMessage.enableMarkdown(true);
        sendMessage.disableWebPagePreview();
        sendMessage.setReplyMarkup(keyboardMarkup);
        return sendMessage;
    }

    private ReplyKeyboardMarkup mainMenuKeyboard() {
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        keyboardMarkup.setOneTimeKeyboard(false);
        keyboardMarkup.setResizeKeyboard(true);
        keyboardMarkup.setSelective(false);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        keyboardRowList.add(addButton(Emojis.BELL + "Add Profile"));
        keyboardRowList.add(addButton(Emojis.TRASH_BIN + "Delete Profile"));
        keyboardRowList.add(addButton(Emojis.SCROLL + "Show Profile List"));
        keyboardRowList.add(addButton(Emojis.QUESTION_MARK + "Help"));

        keyboardMarkup.setKeyboard(keyboardRowList);
        return keyboardMarkup;
    }

    private KeyboardRow addButton(String buttonText) {
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(new KeyboardButton(buttonText));
        return keyboardRow;
    }
}

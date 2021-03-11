package com.telegrambot.codeforcesRatingbot.util;

import com.vdurmont.emoji.EmojiParser;

public enum Emojis {
    BELL(EmojiParser.parseToUnicode(":bell:")),
    SCROLL(EmojiParser.parseToUnicode(":scroll:")),
    FAIL_SERVER_MARK(EmojiParser.parseToUnicode(":x:")),
    TRASH_BIN(EmojiParser.parseToUnicode(":wastebasket:")),
    ROBOT_FACE(EmojiParser.parseToUnicode(":bot_face:")),
    FAIL_USER_MARK(EmojiParser.parseToUnicode(":no_good:")),
    QUESTION_MARK(EmojiParser.parseToUnicode(":question:")),
    ADVICE(EmojiParser.parseToUnicode(":information_source:")),
    UPWARD_TREND(EmojiParser.parseToUnicode(":chart_with_upwards_trend:")),
    DOWNWARD_TREND(EmojiParser.parseToUnicode(":chart_with_downwards_trend:")),
    NEW(EmojiParser.parseToUnicode(":new:")),
    SUCCESS_MARK(EmojiParser.parseToUnicode(":white_check_mark:"));


    private String emojiName;

    @Override
    public String toString() {
        return emojiName;
    }

    Emojis(String emojiName) {
        this.emojiName = emojiName;
    }
}

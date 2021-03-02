package com.telegrambot.codeforcesRatingbot.Types;

import com.telegrambot.codeforcesRatingbot.Types.Chat;

public class Message {
    int message_id;
    int date;
    Chat chat;

    public Message() {
    }

    public int getMessage_id() {
        return message_id;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message_id=" + message_id +
                ", date=" + date +
                ", chat=" + chat +
                ", text='" + text + '\'' +
                '}';
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    String text;
}

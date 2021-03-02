package com.telegrambot.codeforcesRatingbot.Types;

public class Chat {
    int id;
    String type;

    public Chat() {
    }

    @Override
    public String toString() {
        return "Chat{" +
                "id=" + id +
                ", type='" + type + '\'' +
                '}';
    }

    public Chat(int id, String type) {
        this.id = id;
        this.type = type;
    }
}

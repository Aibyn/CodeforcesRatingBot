package com.telegrambot.codeforcesRatingbot.Types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class Update {
    int id;
    Message message;

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Update{" +
                "id=" + id +
                ", message=" + message +
                '}';
    }

    public Update() {
    }

    public Update(int id) {
        this.id = id;
    }
}

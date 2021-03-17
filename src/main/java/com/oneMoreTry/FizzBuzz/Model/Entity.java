package com.oneMoreTry.FizzBuzz.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.UUID;

public class Entity {
    private final String response;
    private final int responseNumber;
    private final String date;
    private final UUID id;

    public Entity(@JsonProperty("id") UUID id,
                  @JsonProperty("response") String response,
                  @JsonProperty("number_of_response") int responseNumber,
                  @JsonProperty("date") String date) {
        this.id = id;
        this.response = response;
        this.responseNumber = responseNumber;
        this.date = date;
    }

//    public Entity(){}

    public String getResponse() {
        return response;
    }

    public int getResponseNumber() {
        return responseNumber;
    }

    public String getDate() {
        return date;
    }

    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Entity{" +
                ", response='" + response + '\'' +
                ", responseNumber=" + responseNumber +
                '}';
    }
}


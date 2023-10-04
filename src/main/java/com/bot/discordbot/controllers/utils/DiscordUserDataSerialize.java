package com.bot.discordbot.controllers.utils;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@NoArgsConstructor
public class DiscordUserDataSerialize {
    private String access_token;

    public DiscordUserDataSerialize(String access_token) {
        this.access_token = access_token;
    }

    @JsonGetter("access_token")
    public String getAccessToken() {
        return access_token;
    }

}

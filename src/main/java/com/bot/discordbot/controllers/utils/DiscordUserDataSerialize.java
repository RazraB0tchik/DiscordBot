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
    private Long create_date;

    public DiscordUserDataSerialize(String access_token, Long create_date) {
        this.access_token = access_token;
        this.create_date = create_date;
    }

    @JsonGetter("access_token")
    public String getAccessToken() {
        return access_token;
    }

    @JsonGetter("create_date")
    public Long getCreate_date() {
        return create_date;
    }
}

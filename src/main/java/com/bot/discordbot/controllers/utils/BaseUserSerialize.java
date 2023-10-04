package com.bot.discordbot.controllers.utils;

import com.fasterxml.jackson.annotation.JsonGetter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class BaseUserSerialize {

    public BaseUserSerialize(Long username) {
        this.username = username;
    }

    private Long username;

    @JsonGetter("username")
    public Long getUsername() {
        return username;
    }
}

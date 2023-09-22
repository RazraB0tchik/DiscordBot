package com.bot.discordbot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor

public class DiscordConfigs extends Configs {
    private String client_id;
    private String auth_url;
    private String bot_token;
    private String redirect_url;
    private String client_secret;

    public DiscordConfigs(String clientId, String authUrl, String redirectUrl, String clientSecret, String botToken) {
        super(clientId, authUrl, redirectUrl, clientSecret);
        bot_token = botToken;
    }
}

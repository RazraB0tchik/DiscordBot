package com.bot.discordbot.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class ConfigsDTO{
    String client_id;
    String auth_url;
    String redirect_url;
    String client_secret;

    public ConfigsDTO(String clientId, String authUrl, String redirectUrl, String clientSecret) {
        client_id = clientId;
        auth_url = authUrl;
        redirect_url = redirectUrl;
        client_secret = clientSecret;
    }
}

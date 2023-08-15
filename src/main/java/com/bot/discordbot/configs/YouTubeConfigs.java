package com.bot.discordbot.configs;

import com.bot.discordbot.dto.YouTbConfigsDTO;
import com.bot.discordbot.services.youtube.YouTubeOauth;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


@Configuration
public class YouTubeConfigs {

    public static String authUrl;

    public static YouTbConfigsDTO secret;

    @Bean
    public void initConfigs(){
        ObjectMapper objectMapper = new ObjectMapper();
        URL configJSON = YouTubeConfigs.class.getResource("/client_secret.json");
        System.out.println(configJSON);
        try {

            secret = objectMapper.readValue(configJSON, YouTbConfigsDTO.class);

            authUrl = "https://accounts.google.com/o/oauth2/v2/auth?" +
                    "scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fyoutube.readonly&" +
                    "access_type=offline&" +
                    "include_granted_scopes=true&" +
                    "state=state_parameter_passthrough_value&" +
                    "redirect_uri=" + secret.getRedirect_uris().get(0) + "&" +
                    "response_type=code&" +
                    "client_id="+ secret.getClient_id();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
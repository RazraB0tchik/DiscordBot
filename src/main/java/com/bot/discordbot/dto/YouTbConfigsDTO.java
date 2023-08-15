package com.bot.discordbot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class YouTbConfigsDTO {

    private String client_id;
    private String project_id;
    private String auth_uri;
    private String token_uri;
    private String auth_provider_x509_cert_url;
    private String client_secret;
    private ArrayList<String> redirect_uris;
    private ArrayList<String> javascript_origins;

}

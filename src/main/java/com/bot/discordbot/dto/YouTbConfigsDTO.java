package com.bot.discordbot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class YouTbConfigsDTO extends ConfigsDTO {

    private String client_id;
    private String project_id;
    private String auth_url;
    private String token_url;
    private String auth_provider_x509_cert_url;
    private String client_secret;
    private String redirect_uris;
    private ArrayList<String> javascript_origins;

    public YouTbConfigsDTO(String clientId, String authUrl, String redirectUrl, String clientSecret, String projectId, String tokenUrl, String authProviderX509CertUrl, ArrayList<String> javascriptOrigins) {
        super(clientId, authUrl, redirectUrl, clientSecret);
        project_id = projectId;
        token_url = tokenUrl;
        auth_provider_x509_cert_url = authProviderX509CertUrl;
        javascript_origins = javascriptOrigins;
    }
}

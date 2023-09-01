package com.bot.discordbot.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.stereotype.Component;

@JsonIgnoreProperties(ignoreUnknown = true)
@Component
@Data
public class OauthCode {
    private String code;
    private String fingerprint;
}

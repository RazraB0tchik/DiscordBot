package com.bot.discordbot.listeners.tools;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class CustomCsrfToken implements CsrfToken {

    private String token;

    private String header;

    public CustomCsrfToken(String token, String header) {
        this.token = token;
        this.header = header;
    }

    @Override
    public String getHeaderName() {
        return header;
    }

    @Override
    public String getParameterName() {
        return null;
    }

    @Override
    public String getToken() {
        return token;
    }
}

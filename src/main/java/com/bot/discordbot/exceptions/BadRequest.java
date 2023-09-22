package com.bot.discordbot.exceptions;

import org.springframework.security.web.firewall.RequestRejectedException;

public class BadRequest extends RequestRejectedException {
    public BadRequest(String message) {
        super(message);
    }
}

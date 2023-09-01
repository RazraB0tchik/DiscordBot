package com.bot.discordbot.errors;

import com.sun.jdi.request.ExceptionRequest;
import org.springframework.security.web.firewall.RequestRejectedException;

public class BadRequest extends RequestRejectedException {
    public BadRequest(String message) {
        super(message);
    }
}

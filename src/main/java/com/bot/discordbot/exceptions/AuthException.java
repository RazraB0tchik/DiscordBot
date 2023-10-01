package com.bot.discordbot.exceptions;

import javax.naming.AuthenticationException;

public class AuthException extends AuthenticationException {
    public AuthException(String explanation) {
        super(explanation);
    }

    public AuthException() {
        super();
    }
}

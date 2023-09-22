package com.bot.discordbot.exceptions;

import javax.naming.AuthenticationException;

public class UserAlreadyExists extends AuthenticationException {
    public UserAlreadyExists(String explanation) {
        super(explanation);
    }

    public UserAlreadyExists() {
        super();
    }
}

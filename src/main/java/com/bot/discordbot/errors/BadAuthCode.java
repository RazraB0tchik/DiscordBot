package com.bot.discordbot.errors;

import javax.naming.AuthenticationException;

public class BadAuthCode extends AuthenticationException {
    public BadAuthCode(String explanation) {
        super(explanation);
    }

    public BadAuthCode() {
        super();
    }
}

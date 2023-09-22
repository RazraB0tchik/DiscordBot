package com.bot.discordbot.exceptions;

import javax.naming.AuthenticationException;

public class DiscordTokensNotFound extends AuthenticationException {
    public DiscordTokensNotFound(String explanation) {
        super(explanation);
    }

    public DiscordTokensNotFound() {
        super();
    }
}

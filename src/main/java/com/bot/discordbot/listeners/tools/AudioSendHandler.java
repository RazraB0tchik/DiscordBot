package com.bot.discordbot.listeners.tools;

import java.nio.ByteBuffer;

public class AudioSendHandler implements net.dv8tion.jda.api.audio.AudioSendHandler {
    @Override
    public boolean canProvide() {
        return false;
    }

    @Override
    public ByteBuffer provide20MsAudio() {
        return null;
    }
}

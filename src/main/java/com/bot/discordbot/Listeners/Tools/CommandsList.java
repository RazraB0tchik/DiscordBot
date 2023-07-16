package com.bot.discordbot.Listeners.Tools;

import org.springframework.stereotype.Component;

@Component
public class CommandsList {

    @Commands(name="play")
    public void play(){
        System.out.println("play");
    }

}



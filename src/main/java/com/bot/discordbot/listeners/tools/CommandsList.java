package com.bot.discordbot.listeners.tools;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import static com.bot.discordbot.configs.YouTubeConfigs.authUrl;

@Service
public class CommandsList {
    @Commands(name="play")
    public void play(SlashCommandInteractionEvent event){
        event.deferReply().queue();
//        System.out.println(discordId);
        event.getHook().sendMessage("Start play after 10 seconds!").queue();
        try {
                Thread.sleep(10000);
            }
        catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        event.getHook().editOriginal("Play!").queue();
    }

    @Commands(name = "auth")
    public void auth(SlashCommandInteractionEvent event) {
        event.deferReply().queue();
        event.getHook().editOriginal("Нажмите на кнопку, чтобы авторизоваться!")
                .setActionRow(
                    Button.primary("authorize", "Авторизоваться через YouTube!")
                 ).queue();

    }


}



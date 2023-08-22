package com.bot.discordbot.listeners.tools;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import static com.bot.discordbot.configs.YouTubeConfigs.authUrl;

@Component
public class CommandsList {
    public static String discordId;

    @Commands(name="play")
    public void play(SlashCommandInteractionEvent event){
        event.deferReply().queue();
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
        discordId = event.getUser().getId();
        event.deferReply().queue();
        event.getHook().editOriginal("Нажмите на кнопку, чтобы авторизоваться!")
                .setActionRow(
                    Button.link(authUrl, "Авторизоваться через YouTube!")
                ).queue();

    }
}



package com.bot.discordbot.listeners.tools;

import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.objects.apps.responses.GetResponse;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommandsList {

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
    public void auth(SlashCommandInteractionEvent event) throws InterruptedException, ClientException, ApiException {
        System.out.println(event.getUser().getName());
        System.out.println(event.getUser().getGlobalName());
        event.deferReply().queue();
        event.getHook().editOriginal("Нажмите на кнопку, чтобы авторизоваться!")
                .setActionRow(
                    Button.link("https://oauth.vk.com/authorize?client_id=51699869&display=page&redirect_uri=http://localhost:5173/authorize&response_type=token&scope=audio&v=5.131", "Авторизоваться через VK!")
//                        https://oauth.vk.com/authorize?client_id=51699869&display=page&redirect_uri=http://localhost:5173/authorize&response_type=token&v=5.131&state=123456
                ).queue();
    }
}



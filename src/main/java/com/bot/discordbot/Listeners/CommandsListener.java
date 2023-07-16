package com.bot.discordbot.Listeners;

import com.bot.discordbot.Listeners.Tools.CommandsList;
import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.messages.MessageCreateData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;

@Component
public class CommandsListener extends ListenerAdapter {

    @Autowired
    CommandsList commandsList;

    HashMap<String, Method> comandsMap = new HashMap<>();

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if(event.getName().equals("play")){
            event.deferReply().queue();
            event.getHook().sendMessage("Start play after 10 seconds!").queue();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            event.getHook().sendMessage("Play!").queue();
        }
    }

    
}

package com.bot.discordbot.listeners;

import com.bot.discordbot.listeners.tools.Commands;
import com.bot.discordbot.listeners.tools.CommandsList;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.HashMap;

@Component
public class CommandsListener extends ListenerAdapter {

    @Autowired
    CommandsList commandsList;

    private HashMap<String, Method> commandsMap = new HashMap<>();

    @Bean
    public void getAllCommands(){
        Class commandsListClass = CommandsList.class;
        for (Method m: commandsListClass.getMethods()){
            if(m.isAnnotationPresent(Commands.class)){
                commandsMap.put(m.getName(), m);
            }
        }
    }

    @SneakyThrows
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        Method method = commandsMap.get(event.getName());
        if(!method.getName().isEmpty()){
            method.invoke(commandsList, event);
        }
    }


}

package com.bot.discordbot.listeners;

import com.bot.discordbot.configs.YouTubeConfigs;
import com.bot.discordbot.listeners.tools.Commands;
import com.bot.discordbot.listeners.tools.CommandsList;
import lombok.SneakyThrows;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.concrete.PrivateChannel;
import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.requests.restaction.CacheRestAction;
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

    public static String discordId;

    @Bean
    public void getAllCommands() {
        Class commandsListClass = CommandsList.class;
        for (Method m : commandsListClass.getMethods()) {
            if (m.isAnnotationPresent(Commands.class)) {
                commandsMap.put(m.getName(), m);
            }
        }
    }

    @SneakyThrows
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        Method method = commandsMap.get(event.getName());
        if (!method.getName().isEmpty()) {
            method.setAccessible(true);
            method.invoke(commandsList, event);
        }
    }

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        Button button = event.getButton();
        System.out.println(discordId);
        if (!event.getUser().isBot()) {
            if (button.getId().equals("authorize")) {
                discordId = event.getUser().getId();
                System.out.println(discordId);
                event.getUser().openPrivateChannel().queue((privateChannel -> {
                    privateChannel.sendMessage(YouTubeConfigs.authUrl).queue();
                }));
            }
        }
    }
}

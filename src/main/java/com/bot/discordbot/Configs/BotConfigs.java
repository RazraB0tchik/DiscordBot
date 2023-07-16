package com.bot.discordbot.Configs;

import com.bot.discordbot.Listeners.CommandsListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BotConfigs {

    @Autowired
    CommandsListener commandsListener;

    @Value("${discord.bot.token}")
    private String token;

    public JDA jda;

    @Bean
    public void createJDA() {
        jda=JDABuilder.createDefault(token).addEventListeners(commandsListener).build();
        jda.updateCommands().addCommands(
                Commands.slash("play", "This command can start playing you music!")
                        .addOption(OptionType.STRING, "trek_name", "Music name", true, true)
        );
    }



}

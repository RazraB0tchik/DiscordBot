package com.bot.discordbot.configs;

import com.bot.discordbot.listeners.CommandsListener;
import com.bot.discordbot.listeners.JoinListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BotConfigs {

    @Autowired
    CommandsListener commandsListener;

    @Autowired
    JoinListener joinListener;

    @Value("${discord.bot.token}")
    private String token;

    public static JDA jda;

    @Bean
    public void createJDA() {
        jda=JDABuilder.createDefault(token)
                .addEventListeners(commandsListener)
                .addEventListeners(joinListener)
                .enableIntents(GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.MESSAGE_CONTENT)
                .enableCache(CacheFlag.VOICE_STATE)
                .setMemberCachePolicy(MemberCachePolicy.VOICE)
                .build();
        jda.updateCommands().addCommands(
                Commands.slash("play", "This command can start playing you music!")
                        .addOption(OptionType.STRING, "trek_name", "Music name", true, true),
                Commands.slash("auth", "This command can authorize you in YouTube")
        ).queue();



    }



}
